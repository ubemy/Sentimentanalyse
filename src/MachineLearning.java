import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;



public class MachineLearning {
	static StanfordCoreNLP pipeline;
	
	public static void init() {
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
/*
	    props.setProperty("pos.model","edu/stanford/nlp/models/pos-tagger/german/german-hgc.tagger");


        props.setProperty("ner.model","edu/stanford/nlp/models/ner/german.ancora.distsim.s512.crf.ser.gz");
        props.setProperty("ner.applyNumericClassifiers","false");
        props.setProperty("ner.useSUTime","false");

        props.setProperty("depparse.model","edu/stanford/nlp/models/parser/nndep/UD_German.gz");*/
	    /*props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/german/german-hgc.tagger");
	    props.setProperty("ner.model", "edu/stanford/nlp/models/ner/german.conll.hgc_175m_600.crf.ser.gz");
	    props.setProperty("ner.applyNumericClassifiers", "false");
	    props.setProperty("ner.useSUTime", "ner.useSUTime");
	    props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/germanFactored.ser.gz");
	    props.setProperty("depparse.model", "edu/stanford/nlp/models/parser/nndep/UD_German.gz");
	    props.setProperty("depparse.language", "german");*/
		//Properties props = PropertiesUtils.asProperties("props", "StanfordCoreNLP-german.properties");
	    pipeline = new StanfordCoreNLP(props);
	}

	public static int findSentiment(String line) {
	    int mainSentiment = 0;
	    if (line != null && line.length() > 0) {
	        int longest = 0;
	        Annotation annotation = pipeline.process(line);
	        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
	        	Tree tree = sentence.get(SentimentAnnotatedTree.class);
	            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);             
	            String partText = sentence.toString();
	            if (partText.length() > longest) {
	                mainSentiment = sentiment;
	                longest = partText.length();
	            }
	        }
	    }
	    return mainSentiment;
    }
}
