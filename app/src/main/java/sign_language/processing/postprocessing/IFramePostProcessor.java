package sign_language.processing.postprocessing;

import sign_language.imaging.IFrame;

public interface IFramePostProcessor {

    IFrame postProcess(IFrame inputFrame);

}
