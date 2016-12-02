from nltk.classify import NaiveBayesClassifier
from nltk.sentiment import SentimentAnalyzer
from nltk.sentiment.util import *
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from nltk.corpus import subjectivity
import os
import codecs

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
    elif word in neutral_words: returnValue = False
    return returnValue

# Trainingsdaten einlesen
def readFilesInCategory(i):
    sentences = []
    categories = []
    testDataLength = len(testDataDirs)
    #for i in range(0, testDataLength):
    path = testDataDirs[i]
    category = testDataCategories[i]
    files = os.listdir(path)
    text_list = []
    for file_name in files:
        f = open(path + "\\" + file_name, "rU", encoding="utf-8", errors="ignore")
        tokens = word_tokenize(f.read())
        lower_tokens = []
        for token in tokens:
            lowerToken = token.lower()
            if(removeUnwantedWords(lowerToken)):
                lower_tokens.append(lowerToken)
        sentences.append(lower_tokens)
        categories.append(category)
        allWords.append(lower_tokens)
        f.close()
    return list(zip(sentences,categories))

def getFeatures(sentence):
    sentenceWords = set(sentence)
    features = {}
    for word in sentenceWords:
        features["contains({})".format(word)] = (word in allWords)
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
    
    sentences_pos = readFilesInCategory(0)
    sentences_neg = readFilesInCategory(1)
    train_docs_pos = sentences_pos[:189]
    train_docs_neg = sentences_neg[:247]
    test_docs_pos = sentences_pos[189:]
    test_docs_neg = sentences_neg[247:]
    train_docs = train_docs_pos+train_docs_neg
    test_docs = test_docs_pos+test_docs_neg

    sentim_analyzer = SentimentAnalyzer()
    sentim_analyzer.add_feat_extractor(getFeatures)
    training_set = sentim_analyzer.apply_features(train_docs, labeled=True)
    test_set = sentim_analyzer.apply_features(test_docs, labeled=True)

    trainer = nltk.NaiveBayesClassifier.train
    classifier = sentim_analyzer.train(trainer, training_set)
    classifier.show_most_informative_features(10)

    for key,value in sorted(sentim_analyzer.evaluate(test_set, f_measure=False, recall=False).items()):
        print('{0}: {1}'.format(key, value))	
        	
    print("Classifying: 'Positiv ist, das gleich Feierabend ist!'", sentim_analyzer.classify('Positiv ist, das gleich Feierabend ist!'))
    print("Classifying: 'Gutes Wetter ist selten geworden.'", sentim_analyzer.classify('Gutes Wetter ist selten geworden.'))
    print("Classifying: 'Zu den negativen Folgen von zu schnellem Fahren gehört unter anderem eine Verminderung des Geschwindigkeitsgefühls.'", sentim_analyzer.classify('Die negativen Folgen von zu schnellem Fahren ist unter anderem eine Verminderung des Geschwindigkeitsgefühls.'))
    print("Classifying: 'Das Beste vom Besten reicht für eine erstklassige Bewertung in den schönsten Gegenden der Welt'", sentim_analyzer.classify('Das Beste vom Besten reicht für eine erstklassige Bewertung in den schönsten Gegenden der Welt'))
    print("Classifying: 'Die Vorlesungen ist um 8:00.'", sentim_analyzer.classify('Die Vorlesungen ist um 8:00.'))

if __name__ == "__main__":
    main()
