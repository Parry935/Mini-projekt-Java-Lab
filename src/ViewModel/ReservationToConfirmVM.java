package ViewModel;

public class ReservationToConfirmVM extends ReservationConfirmVM {
    private String dateToConfirm;


    public ReservationToConfirmVM(String date, String title, String place, String dateToConfirm, String idMovie) {
        super(date,title,place,idMovie);
        this.dateToConfirm = dateToConfirm;
    }


    public String getDateToConfirm() {
        return dateToConfirm;
    }

    public void setDateToConfirm(String dateToConfirm) {
        this.dateToConfirm = dateToConfirm;
    }

}
