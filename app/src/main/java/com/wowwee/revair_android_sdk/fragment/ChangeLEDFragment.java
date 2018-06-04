package com.wowwee.revair_android_sdk.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wowwee.bluetoothrobotcontrollib.revair.REVAirCommandValues;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAir;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAirFinder;
import com.wowwee.revair_android_sdk.R;
import com.wowwee.revair_android_sdk.utils.FragmentHelper;

/**
 * Created by davidchan on 22/3/2017.
 */

public class ChangeLEDFragment extends REVAirBaseFragment {

    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
            return null;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ListView listView = (ListView)view.findViewById(R.id.menuTable);
        String[] ledNameArr = {"Back", "Off", "White", "Red", "Green", "Blue", "Yellow", "Magenta", "Cyan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ledNameArr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (REVAirFinder.getInstance().getREVAirRobotConnectedList().size() > 0) {
                    REVAir robot = (REVAir)REVAirFinder.getInstance().getREVAirRobotConnectedList().get(0);
                    switch (position) {
                        case 0:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new MenuFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Off);
                            break;
                        case 2:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.White);
                            break;
                        case 3:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Red);
                            break;
                        case 4:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Green);
                            break;
                        case 5:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Blue);
                            break;
                        case 6:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Yellow);
                            break;
                        case 7:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Magenta);
                            break;
                        case 8:
                            robot.setLEDRGB(REVAirCommandValues.kREVAirColor.Cyan);
                            break;
                    }
                }
            }
        });
        handler = new Handler();

        return view;
    }


}
