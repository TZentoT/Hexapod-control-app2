package fr.DangerousTraveler.robotcontrol.utils;

import android.bluetooth.BluetoothAdapter;

import java.io.IOException;

import fr.DangerousTraveler.robotcontrol.ControlFragment;
import fr.DangerousTraveler.robotcontrol.activities.MainActivity;

// класс, содержащий методы для управления подключениями bluetooth
public class BluetoothUtils {

    private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    // метод проверки доступности bluetooth на устройстве
    public static boolean isBluetoothAvailable() {

        return mBluetoothAdapter != null;
    }

    // метод передачи положения серводвигателей по bluetooth
    public static void sendDataViaBluetooth(String data) {

        try {
            MainActivity.bluetoothSocket.getOutputStream().write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод инициализации серводвигателей в их калибровочном положении
    public static String initServoPos() {

        String travellingTime = MainActivity.sharedPreferences.getString("settings_travelling_time", "2000");

        // извлечение положений серводвигателей из текстового файла
        int[] servoPos = FilesUtils.readServoPosFromTxt();

        // поместите данные для отправки в строку

        return "#0P" + servoPos[0]
                + "#1P" + servoPos[1]
                + "#4P" + servoPos[2]
                + "#5P" + servoPos[3]
                + "#8P" + servoPos[4]
                + "#9P" + servoPos[5]
                + "#16P" + servoPos[6]
                + "#17P" + servoPos[7]
                + "#20P" + servoPos[8]
                + "#21P" + servoPos[9]
                + "#24P" + servoPos[10]
                + "#25P" + servoPos[11]
                + "T" + travellingTime + "\r";
    }
}