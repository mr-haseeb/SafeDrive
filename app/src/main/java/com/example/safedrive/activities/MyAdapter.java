package com.example.safedrive.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safedrive.R;
import com.example.safedrive.sqlite.Model;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<Model> dataholder;

    public MyAdapter(ArrayList<Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(dataholder.get(position).getId());
        holder.clas.setText((dataholder.get(position).getClas()));
                holder.nose_x.setText((dataholder.get(position).getNose_x()));
                holder.nose_y.setText((dataholder.get(position).getNose_y()));
                holder.left_eye_x.setText((dataholder.get(position).getLeft_eye_x()));
                holder.left_eye_y.setText((dataholder.get(position)).getLeft_eye_y());
                holder.right_eye_x.setText((dataholder.get(position)).getRight_eye_x());
                holder.right_eye_y.setText((dataholder.get(position).getRight_eye_y()));
                holder.left_ear_x.setText((dataholder.get(position)).getLeft_ear_x());
                holder.left_ear_y.setText((dataholder.get(position)).getLeft_ear_y());
                holder.right_ear_x.setText((dataholder.get(position)).getRight_ear_x());
                holder.right_ear_y.setText((dataholder.get(position).getRight_ear_y()));
                holder.left_shoulder_x.setText((dataholder.get(position).getLeft_shoulder_x()));
                holder.left_shoulder_y.setText((dataholder.get(position).getLeft_shoulder_y()));
                holder.right_shoulder_x.setText((dataholder.get(position).getRight_shoulder_x()));
                holder.right_shoulder_y.setText((dataholder.get(position).getRight_shoulder_y()));
                holder.left_elbow_x.setText((dataholder.get(position).getLeft_elbow_x()));
                holder.left_elbow_y.setText((dataholder.get(position).getLeft_elbow_y()));
                holder.right_elbow_x.setText((dataholder.get(position).getRight_elbow_x()));
                holder.right_elbow_y.setText((dataholder.get(position).getRight_elbow_y()));
                holder.left_wrist_x.setText((dataholder.get(position).getLeft_wrist_x()));
                holder.left_wrist_y.setText((dataholder.get(position).getLeft_wrist_y()));
                holder.right_wrist_x.setText((dataholder.get(position).getRight_wrist_x()));
                holder.right_wrist_y.setText((dataholder.get(position).getRight_wrist_y()));
                holder.left_hip_x.setText((dataholder.get(position).getLeft_hip_x()));
                holder.left_hip_y.setText((dataholder.get(position).getLeft_hip_y()));
                holder.right_hip_x.setText((dataholder.get(position).getRight_hip_x()));
                holder.right_hip_y.setText((dataholder.get(position).getRight_hip_y()));
                holder.left_knee_x.setText((dataholder.get(position).getLeft_knee_x()));
                holder.left_knee_y.setText((dataholder.get(position).getLeft_knee_y()));
                holder.right_knee_x.setText((dataholder.get(position).getRight_knee_x()));
                holder.right_knee_y.setText((dataholder.get(position).getRight_knee_y()));
                holder.left_ankle_x.setText((dataholder.get(position).getLeft_ankle_x()));
                holder.left_ankle_y.setText((dataholder.get(position).getLeft_ankle_y()));
                holder.right_ankle_x.setText((dataholder.get(position).getRight_ankle_x()));
                holder.right_ankle_y.setText((dataholder.get(position).getRight_ankle_y()));
                holder.score.setText((dataholder.get(position).getScore()));






    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
{
     TextView id,  clas,  nose_x,  nose_y,  left_eye_x,  left_eye_y,
     right_eye_x,  right_eye_y,  left_ear_x,  left_ear_y,  right_ear_x,  right_ear_y,
     left_shoulder_x,  left_shoulder_y,  right_shoulder_x,  right_shoulder_y,  left_elbow_x,
     left_elbow_y,  right_elbow_x,  right_elbow_y,  left_wrist_x,  left_wrist_y,
     right_wrist_x,  right_wrist_y,  left_hip_x,  left_hip_y,  right_hip_x,  right_hip_y,
     left_knee_x,  left_knee_y,  right_knee_x,  right_knee_y,  left_ankle_x,  left_ankle_y,
     right_ankle_x,  right_ankle_y,  score;




    public MyViewHolder(View itemView){
        super(itemView);


                  id=(TextView)itemView.findViewById(R.id.id) ;
                 clas=(TextView)itemView.findViewById(R.id.clas);
        nose_x = (TextView) itemView.findViewById(R.id.Noise_X);
                nose_y=(TextView)itemView.findViewById(R.id.Noise_Y) ;
                left_eye_x=(TextView)itemView.findViewById(R.id.LEFT_EYE_X);
                left_eye_y=(TextView)itemView.findViewById(R.id.LEFT_EYE_Y);
                right_eye_x=(TextView)itemView.findViewById(R.id.RIGHT_EYE_X);
                right_eye_y=(TextView)itemView.findViewById(R.id.RIGHT_EYE_Y);
                          left_ear_x=(TextView)itemView.findViewById(R.id.LEFT_EAR_X);
                          left_ear_y=(TextView)itemView.findViewById(R.id.LEFT_EAR_Y);
                          right_ear_x=(TextView)itemView.findViewById(R.id.RIGHT_EAR_X);
                          right_ear_y=(TextView)itemView.findViewById(R.id.RIGHT_EAR_Y);
                left_shoulder_x=(TextView)itemView.findViewById(R.id.LEFT_SHOULDER_X);
               left_shoulder_y=(TextView)itemView.findViewById(R.id.LEFT_SHOULDER_Y);
                right_shoulder_x=(TextView)itemView.findViewById(R.id.RIGHT_SHOULDER_X);
                right_shoulder_y=(TextView)itemView.findViewById(R.id.RIGHT_SHOULDER_Y);
                left_elbow_x=(TextView)itemView.findViewById(R.id.LEFT_ELBOW_X);
                left_elbow_y=(TextView)itemView.findViewById(R.id.LEFT_ELBOW_Y);
                right_elbow_x=(TextView)itemView.findViewById(R.id.RIGHT_ELBOW_X);
                right_elbow_y=(TextView)itemView.findViewById(R.id.RIGHT_ELBOW_Y);
                left_wrist_x=(TextView)itemView.findViewById(R.id.LEFT_WRIST_X);
                left_wrist_y=(TextView)itemView.findViewById(R.id.LEFT_WRIST_Y);

                right_wrist_x=(TextView)itemView.findViewById(R.id.RIGHT_WRIST_X);
                right_wrist_y=(TextView)itemView.findViewById(R.id.RIGHT_WRIST_Y);
                left_hip_x=(TextView)itemView.findViewById(R.id.LEFT_HIP_X);
                left_hip_y=(TextView)itemView.findViewById(R.id.LEFT_HIP_Y);
                right_hip_x=(TextView)itemView.findViewById(R.id.RIGHT_HIP_X);
                right_hip_y=(TextView)itemView.findViewById(R.id.RIGHT_HIP_Y);
                left_knee_x=(TextView)itemView.findViewById(R.id.LEFT_KNEE_X);
                left_knee_y=(TextView)itemView.findViewById(R.id.LEFT_KNEE_Y);
                right_knee_x=(TextView)itemView.findViewById(R.id.RIGHT_KNEE_X);
                right_knee_y=(TextView)itemView.findViewById(R.id.RIGHT_KNEE_Y);
                left_ankle_x=(TextView)itemView.findViewById(R.id.LEFT_ANKLE_X);
                left_ankle_y=(TextView)itemView.findViewById(R.id.LEFT_ANKLE_Y);
                right_ankle_x=(TextView)itemView.findViewById(R.id.RIGHT_ANKLE_X);
                right_ankle_y=(TextView)itemView.findViewById(R.id.RIGHT_ANKLE_Y) ;
                score=(TextView)itemView.findViewById(R.id.score);


    }

}
}
