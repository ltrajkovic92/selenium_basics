package p28_09_2023.Uvod.UvodUIzuzetke;

public class FakeDriver { //WebDriver driver


    public int findNextNumber (int n) throws ITBootcampException {
        if (n<0) {
            throw new ITBootcampException("Preterao si! Uneseo si " + n);
        }
        return  n+1;
    }


}
