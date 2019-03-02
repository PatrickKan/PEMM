import os
import cv2


def frameCapture():

    currentpath = os.path.dirname(os.path.dirname(__file__))
    print(currentpath + "/input.mp4")
    vid = cv2.VideoCapture(currentpath + "/input.mp4")

    fps = vid.get(cv2.CAP_PROP_FPS)
    frameCount = int(vid.get(cv2.CAP_PROP_FRAME_COUNT))
    print(frameCount)
    skip = int(frameCount/5)
    count = 0
    success = 1
    while (success) :
        
        success, image = vid.read()
        print(success)
        if(count % skip == 0):
            cv2.imwrite("frame%d.jpg" %(count/skip), image)
        count += 1
        print(str(skip) + " " + str(count))
    return 0


frameCapture()
