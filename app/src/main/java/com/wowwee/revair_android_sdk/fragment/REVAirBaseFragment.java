package com.wowwee.revair_android_sdk.fragment;

import android.support.v4.app.Fragment;

import com.wowwee.bluetoothrobotcontrollib.revair.REVAir;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAirCommandValues;
import com.wowwee.bluetoothrobotcontrollib.revair.REVAirRobotConstant;


/**
 * Created by davidchan on 24/3/2017.
 */

public class REVAirBaseFragment extends Fragment implements REVAir.REVAIRRobotInterface {

    @Override
    public void revairDeviceReady(REVAir revAir) {

    }

    @Override
    public void revairDeviceDisconnected(REVAir revAir) {

    }

    @Override
    public void revairDidReceiveQuadcopterStatus(REVAir revAir, int i) {

    }

    @Override
    public void revairDidReceiveNotifyError(REVAir revAir, REVAirRobotConstant.kREVAirNotifyError kREVAirNotifyError) {

    }

    @Override
    public void revairDidCalibrate(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidReceiveBeaconMode(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidReceiveAltitudeMode(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidReceiveSignalStrength(REVAir revAir, int i) {

    }

    @Override
    public void revairDidReceivePosition(REVAir revAir, int i, int i1, int i2) {

    }

    @Override
    public void revairDidReceiveFirmwareVersion(REVAir revAir, int i, int i1) {

    }

    @Override
    public void revairDidReceiveWallDetected(REVAir revAir, int i) {

    }

    @Override
    public void revairDidReceiveWallDetectionModeResponse(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidReceiveCrashDetectionModeResponse(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidReceiveStallDetectionModeResponse(REVAir revAir, boolean b) {

    }

    @Override
    public void revairDidResetCalibration(REVAir revAir, boolean b) {

    }

    @Override
    public void revairdidReceiveIRCommand(REVAir revAir, int i, REVAirCommandValues.kRevAirRXDirection kRevAirRXDirection) {

    }

    @Override
    public void didNotifyModifiedZ(REVAir revAir, int i) {

    }

    @Override
    public void revairDidNotifyFirstSonar(REVAir revAir) {

    }

    @Override
    public void revairDidReceiveBatteryInfo(REVAir revAir, float v) {

    }
}
