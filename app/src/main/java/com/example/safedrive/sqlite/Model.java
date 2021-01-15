package com.example.safedrive.sqlite;

public class Model {
     String id= "ID";
     String clas= "class";

     String nose_x= "NOSE_X";
     String nose_y= "NOSE_Y";

     String left_eye_x= "LEFT_EYE_X";
     String left_eye_y= "LEFT_EYE_Y";

     String right_eye_x= "RIGHT_EYE_X";
     String right_eye_y= "RIGHT_EYE_Y";

     String left_ear_x= "LEFT_EAR_X";
     String  left_ear_y= "LEFT_EAR_Y";

     String  right_ear_x= "RIGHT_EAR_X";
     String  right_ear_y= "RIGHT_EAR_Y";

     String  left_shoulder_x= "LEFT_SHOULDER_X";
     String  left_shoulder_y= "LEFT_SHOULDER_Y";

     String  right_shoulder_x= "RIGHT_SHOULDER_X";
     String  right_shoulder_y= "RIGHT_SHOULDER_Y";

     String  left_elbow_x= "LEFT_ELBOW_X";
     String  left_elbow_y= "LEFT_ELBOW_Y";

     String  right_elbow_x= "RIGHT_ELBOW_X";
     String  right_elbow_y= "RIGHT_ELBOW_Y";

     String  left_wrist_x= "LEFT_WRIST_X";
     String  left_wrist_y= "LEFT_WRIST_Y";

     String  right_wrist_x= "RIGHT_WRIST_X";
     String  right_wrist_y= "RIGHT_WRIST_Y";

     String  left_hip_x= "LEFT_HIP_X";
     String  left_hip_y= "LEFT_HIP_Y";

     String  right_hip_x= "RIGHT_HIP_X";
     String  right_hip_y= "RIGHT_HIP_Y";

     String  left_knee_x= "LEFT_KNEE_X";
     String  left_knee_y= "LEFT_KNEE_Y";

     String  right_knee_x= "RIGHT_KNEE_X";
     String  right_knee_y= "RIGHT_KNEE_Y";

     String  left_ankle_x= "LEFT_ANKLE_X";
     String  left_ankle_y= "LEFT_ANKLE_Y";

     String  right_ankle_x= "RIGHT_ANKLE_X";
     String  right_ankle_y= "RIGHT_ANKLE_Y";

     String  score= "score";

    public Model(String id, String clas, String nose_x, String nose_y, String left_eye_x, String left_eye_y, String right_eye_x, String right_eye_y, String left_ear_x, String left_ear_y, String right_ear_x, String right_ear_y, String left_shoulder_x, String left_shoulder_y, String right_shoulder_x, String right_shoulder_y, String left_elbow_x, String left_elbow_y, String right_elbow_x, String right_elbow_y, String left_wrist_x, String left_wrist_y, String right_wrist_x, String right_wrist_y, String left_hip_x, String left_hip_y, String right_hip_x, String right_hip_y, String left_knee_x, String left_knee_y, String right_knee_x, String right_knee_y, String left_ankle_x, String left_ankle_y, String right_ankle_x, String right_ankle_y, String score) {
        this.id = id;
        this.clas = clas;
        this.nose_x = nose_x;
        this.nose_y = nose_y;
        this.left_eye_x = left_eye_x;
        this.left_eye_y = left_eye_y;
        this.right_eye_x = right_eye_x;
        this.right_eye_y = right_eye_y;
        this.left_ear_x = left_ear_x;
        this.left_ear_y = left_ear_y;
        this.right_ear_x = right_ear_x;
        this.right_ear_y = right_ear_y;
        this.left_shoulder_x = left_shoulder_x;
        this.left_shoulder_y = left_shoulder_y;
        this.right_shoulder_x = right_shoulder_x;
        this.right_shoulder_y = right_shoulder_y;
        this.left_elbow_x = left_elbow_x;
        this.left_elbow_y = left_elbow_y;
        this.right_elbow_x = right_elbow_x;
        this.right_elbow_y = right_elbow_y;
        this.left_wrist_x = left_wrist_x;
        this.left_wrist_y = left_wrist_y;
        this.right_wrist_x = right_wrist_x;
        this.right_wrist_y = right_wrist_y;
        this.left_hip_x = left_hip_x;
        this.left_hip_y = left_hip_y;
        this.right_hip_x = right_hip_x;
        this.right_hip_y = right_hip_y;
        this.left_knee_x = left_knee_x;
        this.left_knee_y = left_knee_y;
        this.right_knee_x = right_knee_x;
        this.right_knee_y = right_knee_y;
        this.left_ankle_x = left_ankle_x;
        this.left_ankle_y = left_ankle_y;
        this.right_ankle_x = right_ankle_x;
        this.right_ankle_y = right_ankle_y;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getNose_x() {
        return nose_x;
    }

    public void setNose_x(String nose_x) {
        this.nose_x = nose_x;
    }

    public String getNose_y() {
        return nose_y;
    }

    public void setNose_y(String nose_y) {
        this.nose_y = nose_y;
    }

    public String getLeft_eye_x() {
        return left_eye_x;
    }

    public void setLeft_eye_x(String left_eye_x) {
        this.left_eye_x = left_eye_x;
    }

    public String getLeft_eye_y() {
        return left_eye_y;
    }

    public void setLeft_eye_y(String left_eye_y) {
        this.left_eye_y = left_eye_y;
    }

    public String getRight_eye_x() {
        return right_eye_x;
    }

    public void setRight_eye_x(String right_eye_x) {
        this.right_eye_x = right_eye_x;
    }

    public String getRight_eye_y() {
        return right_eye_y;
    }

    public void setRight_eye_y(String right_eye_y) {
        this.right_eye_y = right_eye_y;
    }

    public String getLeft_ear_x() {
        return left_ear_x;
    }

    public void setLeft_ear_x(String left_ear_x) {
        this.left_ear_x = left_ear_x;
    }

    public String getLeft_ear_y() {
        return left_ear_y;
    }

    public void setLeft_ear_y(String left_ear_y) {
        this.left_ear_y = left_ear_y;
    }

    public String getRight_ear_x() {
        return right_ear_x;
    }

    public void setRight_ear_x(String right_ear_x) {
        this.right_ear_x = right_ear_x;
    }

    public String getRight_ear_y() {
        return right_ear_y;
    }

    public void setRight_ear_y(String right_ear_y) {
        this.right_ear_y = right_ear_y;
    }

    public String getLeft_shoulder_x() {
        return left_shoulder_x;
    }

    public void setLeft_shoulder_x(String left_shoulder_x) {
        this.left_shoulder_x = left_shoulder_x;
    }

    public String getLeft_shoulder_y() {
        return left_shoulder_y;
    }

    public void setLeft_shoulder_y(String left_shoulder_y) {
        this.left_shoulder_y = left_shoulder_y;
    }

    public String getRight_shoulder_x() {
        return right_shoulder_x;
    }

    public void setRight_shoulder_x(String right_shoulder_x) {
        this.right_shoulder_x = right_shoulder_x;
    }

    public String getRight_shoulder_y() {
        return right_shoulder_y;
    }

    public void setRight_shoulder_y(String right_shoulder_y) {
        this.right_shoulder_y = right_shoulder_y;
    }

    public String getLeft_elbow_x() {
        return left_elbow_x;
    }

    public void setLeft_elbow_x(String left_elbow_x) {
        this.left_elbow_x = left_elbow_x;
    }

    public String getLeft_elbow_y() {
        return left_elbow_y;
    }

    public void setLeft_elbow_y(String left_elbow_y) {
        this.left_elbow_y = left_elbow_y;
    }

    public String getRight_elbow_x() {
        return right_elbow_x;
    }

    public void setRight_elbow_x(String right_elbow_x) {
        this.right_elbow_x = right_elbow_x;
    }

    public String getRight_elbow_y() {
        return right_elbow_y;
    }

    public void setRight_elbow_y(String right_elbow_y) {
        this.right_elbow_y = right_elbow_y;
    }

    public String getLeft_wrist_x() {
        return left_wrist_x;
    }

    public void setLeft_wrist_x(String left_wrist_x) {
        this.left_wrist_x = left_wrist_x;
    }

    public String getLeft_wrist_y() {
        return left_wrist_y;
    }

    public void setLeft_wrist_y(String left_wrist_y) {
        this.left_wrist_y = left_wrist_y;
    }

    public String getRight_wrist_x() {
        return right_wrist_x;
    }

    public void setRight_wrist_x(String right_wrist_x) {
        this.right_wrist_x = right_wrist_x;
    }

    public String getRight_wrist_y() {
        return right_wrist_y;
    }

    public void setRight_wrist_y(String right_wrist_y) {
        this.right_wrist_y = right_wrist_y;
    }

    public String getLeft_hip_x() {
        return left_hip_x;
    }

    public void setLeft_hip_x(String left_hip_x) {
        this.left_hip_x = left_hip_x;
    }

    public String getLeft_hip_y() {
        return left_hip_y;
    }

    public void setLeft_hip_y(String left_hip_y) {
        this.left_hip_y = left_hip_y;
    }

    public String getRight_hip_x() {
        return right_hip_x;
    }

    public void setRight_hip_x(String right_hip_x) {
        this.right_hip_x = right_hip_x;
    }

    public String getRight_hip_y() {
        return right_hip_y;
    }

    public void setRight_hip_y(String right_hip_y) {
        this.right_hip_y = right_hip_y;
    }

    public String getLeft_knee_x() {
        return left_knee_x;
    }

    public void setLeft_knee_x(String left_knee_x) {
        this.left_knee_x = left_knee_x;
    }

    public String getLeft_knee_y() {
        return left_knee_y;
    }

    public void setLeft_knee_y(String left_knee_y) {
        this.left_knee_y = left_knee_y;
    }

    public String getRight_knee_x() {
        return right_knee_x;
    }

    public void setRight_knee_x(String right_knee_x) {
        this.right_knee_x = right_knee_x;
    }

    public String getRight_knee_y() {
        return right_knee_y;
    }

    public void setRight_knee_y(String right_knee_y) {
        this.right_knee_y = right_knee_y;
    }

    public String getLeft_ankle_x() {
        return left_ankle_x;
    }

    public void setLeft_ankle_x(String left_ankle_x) {
        this.left_ankle_x = left_ankle_x;
    }

    public String getLeft_ankle_y() {
        return left_ankle_y;
    }

    public void setLeft_ankle_y(String left_ankle_y) {
        this.left_ankle_y = left_ankle_y;
    }

    public String getRight_ankle_x() {
        return right_ankle_x;
    }

    public void setRight_ankle_x(String right_ankle_x) {
        this.right_ankle_x = right_ankle_x;
    }

    public String getRight_ankle_y() {
        return right_ankle_y;
    }

    public void setRight_ankle_y(String right_ankle_y) {
        this.right_ankle_y = right_ankle_y;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
