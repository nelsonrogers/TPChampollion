package champollion;

public class ServicePrevu {

        private int volumeTP;
        private int volumeTD;
        private int volumeCM;
    
	// TODO : implémenter cette classe

    public ServicePrevu(int volumeTP, int volumeTD, int volumeCM) {
        this.volumeTP = volumeTP;
        this.volumeTD = volumeTD;
        this.volumeCM = volumeCM;
    }

    public int getVolumeTP() {
        return volumeTP;
    }

    public void setVolumeTP(int volumeTP) {
        this.volumeTP = volumeTP;
    }

    public int getVolumeTD() {
        return volumeTD;
    }

    public void setVolumeTD(int volumeTD) {
        this.volumeTD = volumeTD;
    }

    public int getVolumeCM() {
        return volumeCM;
    }

    public void setVolumeCM(int volumeCM) {
        this.volumeCM = volumeCM;
    }

    
}
