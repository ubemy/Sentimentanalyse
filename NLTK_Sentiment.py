from nltk.classify import NaiveBayesClassifier
from nltk.sentiment import SentimentAnalyzer
from nltk.sentiment.util import *
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from nltk.corpus import subjectivity
import os
import codecs

#Quellen:
#http://www.laurentluce.com/posts/twitter-sentiment-analysis-using-python-and-nltk/
#http://www.nltk.org/book/ch01.html
#https://www.ravikiranj.net/posts/2012/code/how-build-twitter-sentiment-analyzer/

testDataDirs = []
testDataDirs.append("Testdaten\\positive")
testDataDirs.append("Testdaten\\negative")
testDataCategories = []
testDataCategories.append("+")
testDataCategories.append("-")

allWords = []

stop_words = set(stopwords.words('german'))
neutral_words = []

def removeUnwantedWords(word):
    returnValue = True
    if len(word) < 3: returnValue = False
    elif word in stop_words: returnValue = False
    #elif word in neutral_words: returnValue = False
    return returnValue

# Trainingsdaten einlesen
def readFilesInCategory():
    sentences = []
    categories = []
    testDataLength = len(testDataDirs)
    for i in range(0, testDataLength):
        path = testDataDirs[i]
        category = testDataCategories[i]
        files = os.listdir(path)
        text_list = []
        for file_name in files:
            f = open(path + "\\" + file_name, "rU", encoding="utf-8", errors="ignore")
            tokens = word_tokenize(f.read())
            lower_tokens = []
            for token in tokens:
                if removeUnwantedWords(token):
                    lowerToken = token.lower()
                    lower_tokens.append(lowerToken)
                    allWords.append(lowerToken)
            lower_tokens.append(category)
            sentences.append(lower_tokens[:-2])
            categories.append(category)
            f.close()
    return list(zip(sentences,categories))

def getFeatures(sentence):
    #Die 500 am häufigsten vorkommenden Wörter
    word_features = []
    all_words = nltk.FreqDist(allWords)
    for word, frequency in all_words.most_common(500):
        word_features.append(word)
    sentenceWords = set(sentence)
    features = {}
    for word in word_features:
        features["contains(%s)" % word] = (word.lower() in sentenceWords)
    return features

def readNeutralWords():
    path = "Testdaten\\neutral"
    files = os.listdir(path)
    for file_name in files:
        f = open(path + "\\" + file_name, "rU", encoding="utf-8", errors="ignore")
        tokens = word_tokenize(f.read())
        for token in tokens:
            if(token not in neutral_words):
                neutral_words.append(token.lower())
        f.close()

def main():
    readNeutralWords()
    sentences = readFilesInCategory()
    random.shuffle(sentences)
    
    train_docs = sentences

    all_words = nltk.FreqDist(allWords)

    training_set = nltk.classify.apply_features(getFeatures, train_docs)
    
    classifier = nltk.NaiveBayesClassifier.train(training_set)
    classifier.show_most_informative_features(10)

    #Zu Klassifizieren:
    #classifier.classify(getFeatures('<SENTENCE_TO_CLASSIFY'.lower().split()))
    print("Classifying: 'Positiv ist, das gleich Feierabend ist!'", classifier.classify(getFeatures('Positiv ist, das gleich Feierabend ist!'.lower().split())))
    print("Classifying: 'Gutes Wetter ist selten geworden.'", classifier.classify(getFeatures('Gutes Wetter ist selten geworden.'.lower().split())))
    print("Classifying: 'Zu den negativen Folgen von zu schnellem Fahren gehört unter anderem eine Verminderung des Geschwindigkeitsgefühls.'", classifier.classify(getFeatures('Die negativen Folgen von zu schnellem Fahren ist unter anderem eine Verminderung des Geschwindigkeitsgefühls.'.lower().split())))
    print("Classifying: 'Das Beste vom Besten reicht für eine erstklassige Bewertung in den schönsten Gegenden der Welt'", classifier.classify(getFeatures('Das Beste vom Besten reicht für eine erstklassige Bewertung in den schönsten Gegenden der Welt'.lower().split())))
    print("Classifying: 'Die Vorlesungen ist um 8:00. prozent'", classifier.classify(getFeatures('Die Vorlesungen ist um 8:00.'.lower().split())))
    
if __name__ == "__main__":
    main()
