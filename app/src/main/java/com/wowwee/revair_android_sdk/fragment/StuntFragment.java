package com.wowwee.revair_android_sdk.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wowwee.bluetoothrobotcontrollib.revair.REVAir;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAirFinder;
import com.wowwee.revair_android_sdk.R;
import com.wowwee.revair_android_sdk.utils.FragmentHelper;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAirRobotConstant.kREVAirStunt;

/**
 * Created by davidchan on 6/4/2017.
 */

public class StuntFragment extends REVAirBaseFragment {
    Handler handler;
    String[] stuntNameArr;
    ListView listView;

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

        listView = (ListView)view.findViewById(R.id.menuTable);
        String[] arr = {"Back", "Take Off", "kREVAirStuntYawBackAndForth", "kREVAirStuntShortYawLeft", "kREVAirStuntShortYawRight", "kREVAirStuntShortThrustPulse", "kREVAirStuntShortNegThrustPulse", "kREVAirStuntWobbleRoll", "kREVAirStuntWobblePitch", "kREVAirStuntRollPitchL", "kREVAirStuntRollPitchR", "kREVAirStuntPitch", "kREVAirStuntRoll", "kREVAirStuntMoonWalk", "kREVAirStuntSpiralUp", "kREVAirStuntLeftFlip", "kREVAirStuntSwayFrontBack", "kREVAirStuntSwayLeftRight", "kREVAirStuntZigZagUp", "kREVAirStuntZigZagDown", "kREVAirStuntSpiralDown", "kREVAirStuntRightFlip", "kREVAirStuntBackFlip", "kREVAirStuntFrontFlip"};
        stuntNameArr = arr;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, stuntNameArr);
        listView.setAdapter(adapter);

        if (REVAirFinder.getInstance().getREVAirRobotConnectedList().size() > 0) {
            REVAir robot = (REVAir) REVAirFinder.getInstance().getREVAirRobotConnectedList().get(0);
            robot.revairSetBeaconMode(true);
            robot.revairDeactivateFollowMeMode();
            robot.revairSetAltitudeMode(true);
            robot.setCallbackInterface(this);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (REVAirFinder.getInstance().getREVAirRobotConnectedList().size() > 0) {
                    final REVAir robot = (REVAir)REVAirFinder.getInstance().getREVAirRobotConnectedList().get(0);

                    AlertDialog.Builder builder;
                    LinearLayout layout = new LinearLayout(getActivity());
                    final EditText txtV1 = new EditText(getActivity());
                    final EditText txtV2 = new EditText(getActivity());
                    switch (position) {
                        case 0:
                            handler = null;
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new MenuFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            if (stuntNameArr[1].contentEquals("Take Off"))
                                robot.revairLandOrTakeOff(false);
                            else if (stuntNameArr[1].contentEquals("Land"))
                                robot.revairLandOrTakeOff(true);
                            break;
                        case 2:
                            robot.revairPerformStunt(kREVAirStunt.YawBackAndForth, 0, 0);
                            break;
                        case 3:
                            robot.revairPerformStunt(kREVAirStunt.ShortYawLeft, 0, 0);
                            break;
                        case 4:
                            robot.revairPerformStunt(kREVAirStunt.ShortYawRight, 0, 0);
                            break;
                        case 5:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("ShortThrustPulse");
                            txtV1.setHint("Intensity: (0-255)");
                            txtV2.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            layout.addView(txtV2);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    int data2 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    if (txtV2.getText().toString().matches("\\d+")) {
                                        data2 = Integer.parseInt(txtV2.getText().toString());
                                        data2 = Math.max(Math.min(data2, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.ShortThrustPulse, data1, data2);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    robot.revairPerformStunt(kREVAirStunt.ShortThrustPulse, 50, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 6:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("ShortNegThrustPulse");
                            txtV1.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.ShortNegThrustPulse, 0, data1);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    robot.revairPerformStunt(kREVAirStunt.ShortNegThrustPulse, 0, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 7:
                            robot.revairPerformStunt(kREVAirStunt.WobbleRoll, 0, 0);
                            break;
                        case 8:
                            robot.revairPerformStunt(kREVAirStunt.WobblePitch, 0, 0);
                            break;
                        case 9:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("RollPitchL");
                            txtV1.setHint("Degree: (-180-180)");
                            txtV2.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            layout.addView(txtV2);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    int data2 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    if (txtV2.getText().toString().matches("\\d+")) {
                                        data2 = Integer.parseInt(txtV2.getText().toString());
                                        data2 = Math.max(Math.min(data2, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.RollPitchL, data1, data2);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    robot.revairPerformStunt(kREVAirStunt.RollPitchL, 50, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 10:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("RollPitchR");
                            txtV1.setHint("Degree: (-180-180)");
                            txtV2.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            layout.addView(txtV2);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    int data2 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    if (txtV2.getText().toString().matches("\\d+")) {
                                        data2 = Integer.parseInt(txtV2.getText().toString());
                                        data2 = Math.max(Math.min(data2, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.RollPitchR, data1, data2);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    robot.revairPerformStunt(kREVAirStunt.RollPitchR, 50, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 11:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Pitch");
                            txtV1.setHint("Degree: (-180-180)");
                            txtV2.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            layout.addView(txtV2);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    int data2 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    if (txtV2.getText().toString().matches("\\d+")) {
                                        data2 = Integer.parseInt(txtV2.getText().toString());
                                        data2 = Math.max(Math.min(data2, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.Pitch, data1, data2);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtV1.setText("50");
                                    txtV2.setText("50");
                                    robot.revairPerformStunt(kREVAirStunt.Pitch, 50, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 12:
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Roll");
                            txtV1.setHint("Degree: (-180-180)");
                            txtV2.setHint("Duration: 10ms (0-255)");
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.addView(txtV1);
                            layout.addView(txtV2);
                            builder.setView(layout);
                            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int data1 = 0;
                                    int data2 = 0;
                                    if (txtV1.getText().toString().matches("\\d+")) {
                                        data1 = Integer.parseInt(txtV1.getText().toString());
                                        data1 = Math.max(Math.min(data1, 255), 0);
                                    }
                                    if (txtV2.getText().toString().matches("\\d+")) {
                                        data2 = Integer.parseInt(txtV2.getText().toString());
                                        data2 = Math.max(Math.min(data2, 255), 0);
                                    }
                                    robot.revairPerformStunt(kREVAirStunt.Roll, data1, data2);
                                    dialog.cancel();
                                }
                            });
                            builder.setNegativeButton("Fill Default Value", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    robot.revairPerformStunt(kREVAirStunt.Roll, 50, 50);
                                }
                            });
                            builder.show();
                            break;
                        case 13:
                            robot.revairPerformStunt(kREVAirStunt.MoonWalk, 0, 0);
                            break;
                        case 14:
                            robot.revairPerformStunt(kREVAirStunt.SpiralUp, 0, 0);
                            break;
                        case 15:
                            robot.revairPerformStunt(kREVAirStunt.LeftFlip, 0, 0);
                            break;
                        case 16:
                            robot.revairPerformStunt(kREVAirStunt.SwayFrontBack, 0, 0);
                            break;
                        case 17:
                            robot.revairPerformStunt(kREVAirStunt.SwayLeftRight, 0, 0);
                            break;
                        case 18:
                            robot.revairPerformStunt(kREVAirStunt.ZigZagUp, 0, 0);
                            break;
                        case 19:
                            robot.revairPerformStunt(kREVAirStunt.ZigZagDown, 0, 0);
                            break;
                        case 20:
                            robot.revairPerformStunt(kREVAirStunt.SpiralDown, 0, 0);
                            break;
                        case 21:
                            robot.revairPerformStunt(kREVAirStunt.RightFlip, 0, 0);
                            break;
                        case 22:
                            robot.revairPerformStunt(kREVAirStunt.ZigZagUp, 0, 0);
                            break;
                        case 23:
                            robot.revairPerformStunt(kREVAirStunt.BackFlip, 0, 0);
                            break;
                        case 24:
                            robot.revairPerformStunt(kREVAirStunt.FrontFlip, 0, 0);
                            break;
                    }
                }
            }
        });
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getStatus();
            }
        }, 500);

        return view;
    }

    void getStatus() {
        if (REVAirFinder.getInstance().getREVAirRobotConnectedList().size() > 0) {
            REVAir robot = (REVAir) REVAirFinder.getInstance().getREVAirRobotConnectedList().get(0);
            robot.revairGetQuadcopterStatus();
            if (handler != null) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getStatus();
                    }
                }, 500);
            }
        }
    }

    @Override
    public void revairDidReceiveQuadcopterStatus(REVAir revAirRobot, int i) {
        if (i == 0 || i == 2) {
            stuntNameArr[1] = "Take Off";

        }
        else if (i == 1) {
            stuntNameArr[1] = "Land";

        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>)listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
