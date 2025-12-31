/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDateTime;
/**
 *
 * @author asus
 */
public class AlarmModel {
    private final LocalDateTime dateTime;
    private final String sound;
    private boolean active = true;

    public AlarmModel(LocalDateTime dateTime, String sound) {
        this.dateTime = dateTime;
        this.sound = sound;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSound() {
        return sound;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}
