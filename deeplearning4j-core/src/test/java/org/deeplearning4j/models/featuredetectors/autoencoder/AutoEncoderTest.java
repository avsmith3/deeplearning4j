package org.deeplearning4j.models.featuredetectors.autoencoder;

import org.apache.commons.math3.random.MersenneTwister;
import org.deeplearning4j.datasets.fetchers.MnistDataFetcher;
import org.deeplearning4j.nn.api.LayerFactory;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.layers.factory.LayerFactories;
import org.junit.Test;
import org.junit.Ignore;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import static org.junit.Assert.assertEquals;

public class AutoEncoderTest {
        @Test
        @Ignore
        public void testDenoisingAutoEncoder() throws Exception {

                MnistDataFetcher fetcher = new MnistDataFetcher(true);
                NeuralNetConfiguration conf = new NeuralNetConfiguration.Builder().momentum(0.9f)
                        .optimizationAlgo(OptimizationAlgorithm.ITERATION_GRADIENT_DESCENT)
                        .corruptionLevel(0.3)
                        .iterations(100)
                        .lossFunction(LossFunctions.LossFunction.RECONSTRUCTION_CROSSENTROPY).rng(new MersenneTwister(123))
                        .learningRate(1e-1f).nIn(784).nOut(600).build();

                fetcher.fetch(100);
                DataSet d2 = fetcher.next();

                INDArray input = d2.getFeatureMatrix();
                LayerFactory layerFactory = LayerFactories.getFactory(AutoEncoder.class);
                AutoEncoder da = layerFactory.create(conf);
                assertEquals(da.params(),da.params());
                assertEquals(471784,da.params().length());
                da.setParams(da.params());
                da.fit(input);
        }



}
