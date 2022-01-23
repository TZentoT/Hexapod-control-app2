package fr.DangerousTraveler.robotcontrol;

import android.bluetooth.BluetoothAdapter;

import java.io.IOException;
import java.util.Arrays;

import fr.DangerousTraveler.robotcontrol.activities.MainActivity;

// класс, содержащий методы для управления подключениями bluetooth
public class BluetoothUtils {

    private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    // метод проверки доступности bluetooth на устройстве
    public static boolean isBluetoothAvailable() {

        return mBluetoothAdapter != null;
    }

    // метод передачи положения сервоприводов по bluetooth
    public static void sendDataViaBluetooth(String data) {

        try {
            MainActivity.bluetoothSocket.getOutputStream().write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод инициализации сервоприводов в их калибровочном положении
    public static String initServoPos() {

        String travellingTime = MainActivity.sharedPreferences.getString("settings_travelling_time", "2000");

        // извлечение положений сервоприводов из текстового файла
        int[] servoPos = FilesUtils.readServoPosFromTxt();

        // поместите данные для отправки в строку
        String data = "#0P" + servoPos[0]
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

        return data;
    }
}