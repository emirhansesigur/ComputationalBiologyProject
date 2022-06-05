import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        char[] DNA = new char[100000]; // En fazla 100000 uzunluklu DNA ile muhatap olacağımızı varsaydım.

        boolean rasgeleMi = true; // default olarak true verdim degisebilir.

        int kullanicininIstegi;
        int DNAuzunlugu;

        String alinanDNA;

        int N; // kac tane sekans uretilecek
        int L; // sekans uzunlugu


        System.out.print("kac tane sekans uretilecek (N)");
        N = input.nextInt();
        System.out.print("Sekans uzunlugu (L)");
        L = input.nextInt();
        //eşleşme ödülü/eşleşmeme cezası ve indel KULLANICIDAN ALINDI
        //int indel = 1, match = 3, mismatch = 2, enDusukSayi=7;
        double indel, match, mismatch, enDusukSayi;

        System.out.print("İndel: ");
        indel = input.nextDouble();

        System.out.print("Match: ");
        match = input.nextDouble();

        System.out.print("Mismatch: ");
        mismatch = input.nextDouble();

        System.out.print("eslesmeSkorMatrix de gorulecek en dusuk skor: ");
        enDusukSayi = input.nextDouble();


        System.out.print("Klavyeden 1, Rasgele: 0 BAS");

        kullanicininIstegi = input.nextInt();


        input.nextLine();
        if (kullanicininIstegi == 1) {
            rasgeleMi = false;
        } else if (kullanicininIstegi == 0) {
            rasgeleMi = true;
        } else {
            System.out.println("Yanlıs tus girisi\n" +
                    "Program sonlandırılıyor\n" +
                    "Tekrar calıstırınız");
            System.exit(1);
        }

        if (rasgeleMi) { // DNA uzunluguna odaklan
            // DNA yi RASGELE olustur.
            System.out.println("DNA Rasgele OLUSTURULACAKTIR");
            System.out.print("DNA uzunlugunu giriniz: ");
            DNAuzunlugu = input.nextInt();

            int[] a = new int[DNAuzunlugu];

            for (int i = 0; i < DNAuzunlugu; i++) {
                a[i] = (int) (Math.random() * 4);

                if (a[i] == 0) {
                    DNA[i] = 'A';
                } else if (a[i] == 1) {
                    DNA[i] = 'C';
                } else if (a[i] == 2) {
                    DNA[i] = 'G';
                } else if (a[i] == 3) {
                    DNA[i] = 'T';
                }
            }


        } else {
            // DNA hazır VERİLDİ.
            System.out.print("Istediginiz DNA sekansini giriniz: ");

            alinanDNA = input.nextLine();

            for (int i = 0; i < alinanDNA.length(); i++) {
                DNA[i] = alinanDNA.charAt(i);
            }
            DNAuzunlugu = alinanDNA.length();

        }
        // KONTROL AMAÇLI.
        System.out.print("DNA: ");
        for (int i = 0; i < DNAuzunlugu; i++) {
            System.out.print(DNA[i]);
        }
        System.out.println();
        System.out.println("Uzunluku: " + DNAuzunlugu);


        // sekansları olustur.
        // kac tane sayı girecegine gore birbirini tekrar etmeyen sayılar olustur ve uzunluga gore
        // o sayıdan baslayarak ( 0 dan baslat rasgeleleri) L kadar git sonra bunları 2 boyutlu bir diziye at.
        char[][] TumSekanslar = new char[N][L];


        // Sekans olusturuyor.
        for (int i = 0; i < N; i++) { // Top üretilecek sekans 20
            int basPozisyon = (int) (Math.random() * (DNAuzunlugu - L - 1));
            int temp = basPozisyon;
            for (int j = 0; j < L; j++, temp++) {  // sekans uzunlugu 8
                TumSekanslar[i][j] = DNA[temp];
            }

        }

        //Sekans yazdırıyor
        System.out.println("SEKANSLAR");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < L; j++) {
                System.out.print(TumSekanslar[i][j]);
            }
            System.out.println();
        }

        char[][] Complementler = new char[N][L];

        System.out.println();

        // complement işlemi
        System.out.println("complementler");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < L; j++) {
                Complementler[i][j] = TumSekanslar[i][L - j - 1];

                switch (Complementler[i][j]) {
                    case 'A':
                        Complementler[i][j] = 'T';
                        break;
                    case 'C':
                        Complementler[i][j] = 'G';
                        break;
                    case 'G':
                        Complementler[i][j] = 'C';
                        break;
                    case 'T':
                        Complementler[i][j] = 'A';
                        break;
                }


                System.out.print(Complementler[i][j]);

            }
            System.out.println();
        }


        // ESLESME SKORU BULMA.......................****************************
        double[][] eslesmeSkorMatrix = new double[N][N]; //N: kaç tane sekans üretildigi.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                } else if(i<j) { // ust matris
                    eslesmeSkorMatrix[i][j] = overlap(L, indel,match, mismatch, TumSekanslar[i], TumSekanslar[j],enDusukSayi);
                }
                else {
                    eslesmeSkorMatrix[i][j] = overlap(L, indel,match, mismatch, TumSekanslar[i], Complementler[j],enDusukSayi);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%5.2f  ",eslesmeSkorMatrix[i][j]);

            }
            System.out.println();
        }

        DosyayaYazdir(eslesmeSkorMatrix, N);


        // en yüksek skorlu olan ilk elemanı bulma.
        //...

    }

    public static double overlap(int L, double indel, double match, double mismatch, char[] ilkSekans, char[] ikinciSekans, double enDusukSayi) {
        // Ozel ilgi...

        double[][] matris = new double[L + 1][L + 1]; // ilk basta tum satir ve sutunlar 0 olacak
        // O yuzden ilk satır ve sutunu 0 ile kaplamamıza gerek yok.


        for (int i = 1; i < L + 1; i++) {
            for (int j = 1; j < L + 1; j++) {
                // ilk once match mi mismach mi onu tespit ettim.
                double matchorMismatch;


                if (ilkSekans[i - 1] == ikinciSekans[j - 1]) {
                    matchorMismatch = match;
                } else {
                    matchorMismatch = -mismatch;
                }

                matris[i][j] = Math.max(Math.max(matris[i - 1][j] - indel, matris[i][j - 1] - indel), matris[i - 1][j - 1] + matchorMismatch);

            }
        }
        /*
        for (int i = 0; i < L+1; i++) {
            for (int j = 0; j < L+1; j++) {
                System.out.print(matris[i][j] +" ");
            }
            System.out.println();
        }*/

        double bestOverlap = matris[1][L];
        for (int k = 2; k < L + 1; k++) {
            if (matris[k][L] > bestOverlap) {
                bestOverlap = matris[k][L];
            }
        }
        for (int t = 1; t < L + 1 ; t++) {
            if (matris[L][t] > bestOverlap) {
                bestOverlap = matris[L][t];
            }
        }

        if(bestOverlap>=enDusukSayi){
            return bestOverlap;
        }
        else {
            return 0.1;
        }

    }

    public static void DosyayaYazdir(double[][] eslesmeSkorMatrix, int N) throws FileNotFoundException {

        PrintWriter writer=new PrintWriter("C:\\Users\\Emir\\Desktop\\a.txt"); // Buraya ADRES GIRILMELI.

        for (int i = 1; i<=N; i++){
                writer.printf("%5d|",i);

        }
        writer.println();

        for (int i = 0; i<N; i++){
            //writer.printf("     ");
            for(int j = 0; j<N; j++){
                if(i==j){
                    writer.printf("    *|");
                }
                else if(eslesmeSkorMatrix[i][j]==0.1){ // Eger eslesmeSkorMatrix de 0.1 varsa o deger alinmayacaktir.
                    writer.printf("     |");

                }
                else if(eslesmeSkorMatrix[i][j]>=10.0){ // Basamak artisindan satirlarin kaymamasi için kullanildi
                    writer.printf("%3.1f |",eslesmeSkorMatrix[i][j]);
                }
                else {
                    writer.printf("%3.1f  |",eslesmeSkorMatrix[i][j]);

                }
                //System.out.print("|");
            }writer.println();
        }
        {

            try{
                if (writer!=null) writer.close();
            }
            catch (Exception e) {System.out.println("Could not close writer");}
        }
    }


}
