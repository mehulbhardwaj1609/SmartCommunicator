package sign_language.processing.preprocessing;

import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;

import sign_language.imaging.Frame;
import sign_language.imaging.IFrame;
import sign_language.processing.IFrameProcessor;

public class CameraFrameAdapter implements IFramePreProcessor {

    private IFrameProcessor downSampler, resizer;
    private IFrame outputFrame;

    public CameraFrameAdapter(IFrameProcessor downSamplingFrameProcessor,
                              IFrameProcessor resizingFrameProcessor) {
        downSampler = downSamplingFrameProcessor;
        resizer = resizingFrameProcessor;
    }

    @Override
    public IFrame preProcess(CvCameraViewFrame inputFrame) {

        // Create the pre-processed output frame
        outputFrame = new Frame(inputFrame.rgba());

//         Resize image
        outputFrame = resizer.process(outputFrame);

        // Set the original size of the frame
        outputFrame.setOriginalSize(inputFrame.rgba().size());

        // Resize image - currently unused
//        outputFrame = resizer.process(outputFrame);

        // Further downsampling for efficiency
        outputFrame = downSampler.process(outputFrame);

        return outputFrame;
    }

}
