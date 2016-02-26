package com.example.richard.parking_lot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by richard on 1/31/2016.
 */
public class ParkingAdapter extends BaseAdapter {

//    MainActivity mainActivity = new MainActivity();
//    private ProgressBar progressBar;
//    private ProgressBar progressBar1;
//    private ProgressBar progressBar2;
    private Context context;
    private List<String> idList;
    private Map<String, ParkingInfo> studentMap;
    ViewHolder vh;
    ViewHolder1 vh1;
    private ParkingInfo parkingInfo;
    private Firebase firebase;
    ArrayList<ParkingInfo> infoList;
    ListView listView;
//    ArrayList position_list = MainActivity.position_list ;
//    HashSet position_set = MainActivity.position_set;


    public ParkingAdapter() {

    }

    public ParkingAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot, ListView listView) {
        System.out.println("fanfanfanfanfafnafnafn    ");
        this.context = context;
        this.firebase = firebase;
        this.listView = listView;
        initData(dataSnapshot);
        System.out.println("yangyangyangyang    ");
    }

    public ParkingAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot, ArrayList position_list) {
//        this.position_list = position_list;
        this.context = context;
        this.firebase = firebase;
        initDataMark(dataSnapshot);
    }


    private void initDataMark(DataSnapshot dataSnapshot) {

        System.out.println("TEST               "+ dataSnapshot.getKey() + " : " + dataSnapshot.getValue());


        idList = new ArrayList<String>();
        studentMap = new HashMap<String, ParkingInfo>();

        for(DataSnapshot data : dataSnapshot.getChildren()) {
            System.out.println("TEST"+ "data: " + data + " type: " + data.getClass().getName());
            String id = data.getKey();
            ParkingInfo parkingInfo = data.getValue(ParkingInfo.class);
            System.out.println("TEST" + "stu: " + parkingInfo);
            idList.add(id);
            studentMap.put(id, parkingInfo);

        }

        System.out.println("YANGFAN  " + studentMap.get(idList.get(0)).getType());
        System.out.println("YANGFAN ++  " + idList.get(0));

        infoList = new ArrayList<ParkingInfo>();

//        System.out.println("position_list.size()  " + position_list.size());

        Iterator it = MainActivity.position_set.iterator();

        while(it.hasNext()){

            infoList.add(studentMap.get(idList.get((Integer) it.next())));

        }

//        for (int i = 0; i < position_set.size(); i++ ){
//
//            System.out.println("position_list.get(i)  " + position_set.get(i));
////            if (studentMap.get(idList.get(i)).isSelected())
//            infoList.add(studentMap.get(idList.get((Integer) position_list.get(i))));
//        }

//        infoList.add(studentMap.get(idList.get(0)));
//        infoList.add(studentMap.get(idList.get(1)));
//        infoList.add(studentMap.get(idList.get(2)));
    }


    private void initData(DataSnapshot dataSnapshot) {

        System.out.println("TEST               "+ dataSnapshot.getKey() + " : " + dataSnapshot.getValue());


        idList = new ArrayList<String>();
        studentMap = new HashMap<String, ParkingInfo>();

        for(DataSnapshot data : dataSnapshot.getChildren()) {
            System.out.println("TEST"+ "data: " + data + " type: " + data.getClass().getName());
            String id = data.getKey();
            ParkingInfo parkingInfo = data.getValue(ParkingInfo.class);
            System.out.println("TEST" + "stu: " + parkingInfo);
            idList.add(id);
            studentMap.put(id, parkingInfo);
        }

        System.out.println("YANGFAN  " + studentMap.get(idList.get(0)).getType());
        System.out.println("YANGFAN ++  " + idList.get(0));

        infoList = new ArrayList<ParkingInfo>();

        for (int i = 0; i < idList.size(); i++ ){
            infoList.add(studentMap.get(idList.get(i)));
        }

//        infoList.add(studentMap.get(idList.get(0)));
//        infoList.add(studentMap.get(idList.get(1)));
//        infoList.add(studentMap.get(idList.get(2)));
    }

//    parkingInfo = (ParkingInfo) getItem(position);




//    {{
//
//        add((new ParkingInfo("106: Parking Structure",50,60,15,25,1)));
//        add(studentMap.get(idList.get(0)));
//        add((new ParkingInfo("B",50,60,0)));
//        add((new ParkingInfo("C",50,60,0)));
//        add((new ParkingInfo("E1",50,60,0)));
//        add((new ParkingInfo("E2",50,60,70,80,1)));
//        add((new ParkingInfo("F1",50,55,0)));
//        add((new ParkingInfo("F2",50,60,0)));
//        add((new ParkingInfo("F3",50,60,0)));
//        add((new ParkingInfo("F4",50,60,0)));
//        add((new ParkingInfo("F5",50,60,0)));
//        add((new ParkingInfo("Unpaved Overflow Lot",50,65,0)));
//        add((new ParkingInfo("Overflow Parking Lot",50,60,0)));
//        add((new ParkingInfo("A",50,60,0)));
//    }};



    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {




        int type = getItemViewType(position); //

//        if (convertView == null){  不需要判断是否为空，因为有两种不同的parking_list_item
            switch (type){
                case 0:
                    vh = new ViewHolder();
                    //            把parking_list_item 装进 convertView
                    convertView = LayoutInflater.from(context).inflate(R.layout.parking_list_item,parent,false);
                    vh.iv_lotName = (TextView) convertView.findViewById(R.id.name);
                    vh.iv_availNo = (TextView) convertView.findViewById(R.id.availNO);
                    vh.iv_availPer = (TextView) convertView.findViewById(R.id.percent_avail);
                    vh.progressBar = (ProgressBar) convertView.findViewById(R.id.my_progress);
                    vh.iv_checkbox = (CheckBox) convertView.findViewById(R.id.checkbox1);
                    convertView.setTag(vh);


//                    vh.iv_checkbox.setOnClickListener( new View.OnClickListener() {
//                        public void onClick(View v) {
//                            CheckBox cb = (CheckBox) v ;
//                            ParkingInfo ParkingInfo = (ParkingInfo) cb.getTag();
//
//                            System.out.println("cb.getTag()     " + cb.getTag());
//                            System.out.println("cb.getText()    " + cb.getText());
//
//
////                            ParkingInfo.setSelected(cb.isChecked());
//                        }
//                    });

                    vh.iv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int pos = listView.getPositionForView(buttonView);
//                            System.out.println("pos   " + pos);
//                            position_list.add(pos);
//                            System.out.println("position_list.toArray()" + position_list.get(1));
                            if (isChecked)
                                MainActivity.position_set.add(pos);
                            else
                                MainActivity.position_set.remove(pos);

//                              往firebase里写入是否check
//                            if (pos != ListView.INVALID_POSITION){
//                                firebase.child("parking/"+ pos + "/selected").setValue(isChecked);
//                            }
                        }
                    });


                    break;
                case 1:
                    vh1 = new ViewHolder1();
                    //            把multi_parking_list_item 装进 convertView
//                    inflater = LayoutInflater.from(context);
                    convertView = LayoutInflater.from(context).inflate(R.layout.multi_parking_list_item,parent,false);

                    vh1.iv_lotName = (TextView) convertView.findViewById(R.id.name2);
                    vh1.iv_availNo = (TextView) convertView.findViewById(R.id.availNO1);
                    vh1.iv_availPer = (TextView) convertView.findViewById(R.id.percent_avail1);
                    vh1.iv_availNo2 = (TextView) convertView.findViewById(R.id.availNO2);
                    vh1.iv_availPer2 = (TextView) convertView.findViewById(R.id.percent_avail2);
                    vh1.progressBar1 = (ProgressBar) convertView.findViewById(R.id.my_progress1);
                    vh1.progressBar2 = (ProgressBar) convertView.findViewById(R.id.my_progress2);
                    vh1.iv_checkbox2 = (CheckBox) convertView.findViewById(R.id.checkbox2);
                    convertView.setTag(vh1);


//                    vh1.iv_checkbox2.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            CheckBox cb = (CheckBox) v;
//                            ParkingInfo ParkingInfo = (ParkingInfo) cb.getTag();
//
//                            System.out.println("cb.getTag() multi     " + cb.getTag());
//                            System.out.println(" cb.getText() multi   " + cb.getText());
//
//
////                            ParkingInfo.setSelected(cb.isChecked());
//                        }
//                    });
                    vh1.iv_checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int pos = listView.getPositionForView(buttonView);
//                            System.out.println("pos   " + pos);
//
//                            position_list.add(pos);
//                            System.out.println("position_list 1 1.toArray()" + position_list.get(0));

                            if (isChecked)
                                MainActivity.position_set.add(pos);
                            else
                                MainActivity.position_set.remove(pos);

//                            if (pos != ListView.INVALID_POSITION){
//                                firebase.child("parking/"+ pos + "/selected").setValue(isChecked);
//                            }
                        }
                    });

                    break;
            }

//        }else {
//            switch (type){
//                case 0:
//                    vh = (ViewHolder) convertView.getTag();
//                    break;
//                case 1:
//                    vh1 = (ViewHolder1) convertView.getTag();
//                    break;
//            }
//
//        }

       //得到列表第position个parkingInfo
         parkingInfo = infoList.get(position);


        switch (type){
            case 0:
                //        设置position对应text
                vh.iv_lotName.setText(parkingInfo.getLotName());
                vh.iv_availNo.setText(parkingInfo.getAvailNo() + "");
                vh.iv_availPer.setText(parkingInfo.getAvailPer() + "");
                vh.progressBar.setProgress((int) parkingInfo.getAvailPer());
                break;
            case 1:
                vh1.iv_lotName.setText(parkingInfo.getLotName());
                vh1.iv_availNo.setText(parkingInfo.getAvailNo()+"");
                vh1.iv_availPer.setText(parkingInfo.getAvailPer()+"");
                vh1.iv_availNo2.setText(parkingInfo.getAvailNo2()+"");
                vh1.iv_availPer2.setText(parkingInfo.getAvailPer2()+"");
                vh1.progressBar1.setProgress((int) parkingInfo.getAvailPer());
                vh1.progressBar2.setProgress((int) parkingInfo.getAvailPer2());
//                System.out.println(parkingInfo.getLotName()+"sdsdsdd");
//                System.out.println(parkingInfo.getAvailNo());
//                System.out.println(parkingInfo.getAvailPer());
//                System.out.println(parkingInfo.getAvailNo2());
//                System.out.println(parkingInfo.getAvailPer2());
                break;
        }

//        convertView.getBackground().setAlpha(50);

        return convertView;
    }

    public int getItemViewType(int position) {

        return infoList.get(position).getType();
    }

    static class ViewHolder{
        TextView iv_lotName;
        TextView iv_availNo;
        TextView iv_availPer;
        ProgressBar progressBar;
        CheckBox iv_checkbox;
    }

    static class ViewHolder1{
        TextView iv_lotName;
        TextView iv_availNo;
        TextView iv_availPer;
        TextView iv_availNo2;
        TextView iv_availPer2;
        ProgressBar progressBar1;
        ProgressBar progressBar2;
        CheckBox iv_checkbox2;
    }
}
