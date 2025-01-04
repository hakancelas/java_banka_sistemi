import java.util.Scanner;

public class BankaSistemi {
    // Kullanıcı bilgilerini saklamak için değişkenler
    private String kullaniciAdi; // Kullanıcının giriş yapacağı kullanıcı adı
    private String sifre;       // Hesaba erişim için gerekli şifre
    private double bakiye;      // Kullanıcının hesabındaki para miktarı

    // Yapıcı metod (Constructor): Hesap oluşturmak için kullanıcıdan alınan bilgileri saklar
    public BankaSistemi(String kullaniciAdi, String sifre, double bakiye) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.bakiye = bakiye;
    }

    // Kullanıcının hesaba para yatırmasını sağlayan metot
    public void paraYatir(double miktar) {
        if (miktar > 0) { // Sadece pozitif bir miktar girilmesine izin verilir
            bakiye += miktar; // Hesap bakiyesine yatırılan miktar eklenir
            System.out.println("Başarıyla " + miktar + " TL yatırıldı. Güncel bakiyeniz: " + bakiye + " TL");
        } else {
            System.out.println("Geçersiz miktar! Lütfen pozitif bir değer girin.");
        }
    }

    // Kullanıcının hesaptan para çekmesini sağlayan metot
    public void paraCek(double miktar) {
        if (miktar > bakiye) { // Çekilmek istenen miktar bakiyeden fazlaysa işlem reddedilir
            System.out.println("Yetersiz bakiye! Mevcut bakiyeniz: " + bakiye + " TL");
        } else if (miktar <= 0) { // Negatif veya sıfır değer girilirse hata mesajı gösterilir
            System.out.println("Geçersiz miktar! Lütfen pozitif bir değer girin.");
        } else {
            bakiye -= miktar; // Hesaptan çekilen miktar düşülür
            System.out.println("Başarıyla " + miktar + " TL çekildi. Güncel bakiyeniz: " + bakiye + " TL");
        }
    }

    // Kullanıcının mevcut bakiyesini görüntülemesini sağlayan metot
    public void bakiyeGoruntule() {
        System.out.println("Güncel bakiyeniz: " + bakiye + " TL");
    }

    // Hesaba faiz ekleyen metot (örneğin yıllık faiz hesaplama)
    public void faizHesapla(double faizOrani) {
        if (faizOrani > 0) { // Pozitif faiz oranı kontrolü
            double faizMiktari = bakiye * (faizOrani / 100); // Faiz miktarını hesapla
            bakiye += faizMiktari; // Hesaba faiz eklenir
            System.out.println("Faiz oranı: " + faizOrani + "%");
            System.out.println("Eklenen faiz miktarı: " + faizMiktari + " TL");
            System.out.println("Güncel bakiyeniz: " + bakiye + " TL");
        } else {
            System.out.println("Geçersiz faiz oranı! Lütfen pozitif bir değer girin.");
        }
    }

    // Giriş yapma doğrulaması yapan metot
    public boolean girisYap(String kullaniciAdi, String sifre) {
        // Kullanıcı adı ve şifre eşleşirse true döner
        return this.kullaniciAdi.equals(kullaniciAdi) && this.sifre.equals(sifre);
    }

    // Ana metot: Program buradan çalışmaya başlar
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Yeni bir hesap oluşturma süreci
        System.out.println("Banka hesabı oluşturun:");
        System.out.print("Kullanıcı adı: ");
        String kullaniciAdi = scanner.nextLine(); // Kullanıcıdan kullanıcı adı al
        System.out.print("Şifre: ");
        String sifre = scanner.nextLine(); // Kullanıcıdan şifre al
        System.out.print("Başlangıç bakiyesi: ");
        double bakiye = scanner.nextDouble(); // Kullanıcıdan başlangıç bakiyesi al

        // Kullanıcı bilgileri ile yeni bir hesap oluştur
        BankaSistemi hesap = new BankaSistemi(kullaniciAdi, sifre, bakiye);

        System.out.println("\nHesap başarıyla oluşturuldu!");

        // Kullanıcı giriş yapma süreci
        System.out.println("\nLütfen giriş yapın:");
        boolean girisBasarili = false; // Giriş durumunu tutan değişken
        for (int i = 0; i < 3; i++) { // Kullanıcıya 3 deneme hakkı tanınır
            System.out.print("Kullanıcı adı: ");
            String girisKullaniciAdi = scanner.next(); // Kullanıcıdan giriş için kullanıcı adı al
            System.out.print("Şifre: ");
            String girisSifre = scanner.next(); // Kullanıcıdan giriş için şifre al

            // Giriş başarılıysa döngüden çık
            if (hesap.girisYap(girisKullaniciAdi, girisSifre)) {
                System.out.println("Giriş başarılı! Hoş geldiniz, " + kullaniciAdi);
                girisBasarili = true;
                break;
            } else {
                // Hatalı girişlerde kalan hak sayısını belirt
                System.out.println("Hatalı kullanıcı adı veya şifre. Kalan deneme hakkınız: " + (2 - i));
            }
        }

        // 3 başarısız deneme durumunda sistemden çıkılır
        if (!girisBasarili) {
            System.out.println("3 başarısız deneme! Sistemden çıkılıyor.");
            return;
        }

        // Kullanıcıya işlem menüsü gösterilir
        int secim;
        do {
            System.out.println("\nLütfen bir işlem seçin:");
            System.out.println("1. Para Yatır");
            System.out.println("2. Para Çek");
            System.out.println("3. Bakiye Görüntüle");
            System.out.println("4. Faiz Hesapla");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt(); // Kullanıcıdan seçim al

            switch (secim) {
                case 1: // Para yatırma
                    System.out.print("Yatırmak istediğiniz miktarı girin: ");
                    double yatirMiktar = scanner.nextDouble();
                    hesap.paraYatir(yatirMiktar);
                    break;
                case 2: // Para çekme
                    System.out.print("Çekmek istediğiniz miktarı girin: ");
                    double cekMiktar = scanner.nextDouble();
                    hesap.paraCek(cekMiktar);
                    break;
                case 3: // Bakiye görüntüleme
                    hesap.bakiyeGoruntule();
                    break;
                case 4: // Faiz hesaplama
                    System.out.print("Faiz oranını girin (%): ");
                    double faizOrani = scanner.nextDouble();
                    hesap.faizHesapla(faizOrani);
                    break;
                case 5: // Çıkış
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    break;
                default: // Geçersiz seçim durumu
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        } while (secim != 5); // Kullanıcı çıkışı seçene kadar menü gösterilmeye devam edilir

        scanner.close(); // Kaynakları serbest bırak
    }
}