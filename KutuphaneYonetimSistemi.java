import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ANA SINIF: Projenin giriş noktası ve konsol arayüzünü yöneten ana sınıf.
public class KutuphaneYonetimSistemi {

    // Uygulamanın temel yönetim nesneleri ve girdi okuyucusu
    private static Kutuphane kutuphane = new Kutuphane();
    private static Scanner scanner = new Scanner(System.in);

    // ----------------------------------------------------------------
    // ANA PROGRAM BAŞLANGICI - main Metodu
    // ----------------------------------------------------------------
    public static void main(String[] args) {

        // BAŞLANGIÇ VERİLERİ: Örnek Kitap ve Üye Nesneleri Oluşturma
        // NESNE OLUŞTURMA (Instantiation): Kitap ve Uye sınıflarından somut nesneler türetildi.
        kutuphane.kitapEkle(new Kitap("978-01", "Sefiller", "Victor Hugo", 1862));
        kutuphane.kitapEkle(new Kitap("978-02", "Suc ve Ceza", "Dostoyevski", 1866));

        // KALITIM VE ÇOK BİÇİMLİLİK: Alt sınıf nesneleri, üst sınıf referansı (Uye) altında toplanıyor.
        kutuphane.uyeEkle(new StandartUye(101, "Ali", "Yılmaz"));
        kutuphane.uyeEkle(new AkademikUye(201, "Deniz", "Kaya"));

        System.out.println("### NESNEYE YÖNELİK PROGRAMLAMA PROJESİ: KÜTÜPHANE SİSTEMİ ###");
        int secim;

        do {
            gosterMenuyu();
            if (scanner.hasNextInt()) {
                secim = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("HATA: Menü girişi geçersiz. Lütfen sadece menü numarasını giriniz.");
                scanner.nextLine(); // Hatalı girişi temizle
                secim = -1;
                continue;
            }

            // switch/case yapısı ile kullanıcı girdisine göre ilgili işlev çağrılır.
            switch (secim) {
                case 1: kutuphane.tumKitaplariListele(); break;
                case 2: kitapEkle(); break;
                case 3: kitapSil(); break;
                case 4: kutuphane.tumUyeleriListele(); break;
                case 5: uyeEkle(); break;
                case 6: uyeSil(); break;
                case 7: oduncAl(); break;
                case 8: iadeEt(); break;
                case 0: System.out.println("Uygulama sonlandırılıyor."); break;
                default: System.out.println("Bilinmeyen seçim. Tekrar deneyiniz.");
            }
        } while (secim != 0);
    }

    // [YARDIMCI METOTLAR] - Kullanıcı arayüzü ve girdi alma işlevleri
    private static void gosterMenuyu() {
        System.out.println("\n--- İŞLEMLER --- (NYP Kavramları ile Eşleştirilmiştir)");
        System.out.println("1. Kitapları Listele (KAPSÜLLEME prensibi ile verilere erişim)");
        System.out.println("2. Yeni Kitap Ekleme (NESNE oluşturma)");
        System.out.println("3. Kitap Silme");
        System.out.println("4. Üyeleri Listele (KALITIM hiyerarşisinin gösterimi)");
        System.out.println("5. Yeni Üye Ekleme");
        System.out.println("6. Üye Silme");
        System.out.println("7. Kitap Ödünç Alma (ÇOK BİÇİMLİLİK'in en net kullanımı)");
        System.out.println("8. Kitap İade Etme");
        System.out.println("0. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    // Girdi alma metotları (Kodu sadeleştirmek için ana metottan ayrılmıştır)
    private static void kitapEkle() {
        System.out.print("Yeni Kitap ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Başlık: ");
        String baslik = scanner.nextLine();
        System.out.print("Yazar: ");
        String yazar = scanner.nextLine();
        System.out.print("Yayın Yılı: ");
        int yil = scanner.nextInt();
        scanner.nextLine();

        kutuphane.kitapEkle(new Kitap(isbn, baslik, yazar, yil)); // Yeni nesne Kütüphane listesine ekleniyor.
    }

    private static void kitapSil() {
        System.out.print("Silinecek kitabın ISBN'si: ");
        String isbn = scanner.nextLine();
        kutuphane.kitapSil(isbn);
    }

    private static void uyeEkle() {
        System.out.print("Üye No: ");
        int no = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Soyad: ");
        String soyad = scanner.nextLine();
        System.out.print("Üye Türü (1: Standart, 2: Akademik): ");
        int tur = scanner.nextInt();
        scanner.nextLine();

        // Polymorphism (Çok Biçimlilik) için temel tipte (Uye) bir referans kullanılarak nesne oluşturulur.
        if (tur == 1) {
            kutuphane.uyeEkle(new StandartUye(no, ad, soyad));
        } else if (tur == 2) {
            kutuphane.uyeEkle(new AkademikUye(no, ad, soyad));
        } else {
            System.out.println("HATA: Geçersiz üye türü girişi yapıldı.");
        }
    }

    private static void uyeSil() {
        System.out.print("Silinecek üyenin No'su: ");
        int no = scanner.nextInt();
        scanner.nextLine();
        kutuphane.uyeSil(no);
    }

    private static void oduncAl() {
        System.out.print("İşlem yapacak Üye No: ");
        int uyeNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ödünç alınacak Kitap ISBN: ");
        String isbn = scanner.nextLine();

        // Kutuphane sınıfındaki ana yönetim metodu çağrılır. Çok Biçimlilik burada tetiklenir.
        kutuphane.kitapOduncAl(uyeNo, isbn);
    }

    private static void iadeEt() {
        System.out.print("İade edilecek Kitap ISBN: ");
        String isbn = scanner.nextLine();
        kutuphane.kitapIadeEt(isbn);
    }


    // ----------------------------------------------------------------
    // 1. Kitap Sınıfı (NYP: KAPSÜLLEME)
    // ----------------------------------------------------------------
    static class Kitap {
        // KAPSÜLLEME: Tüm veri alanları 'private' tanımlanmıştır. Bu, verilere sınıf dışından doğrudan müdahaleyi engeller.
        private String ISBN;
        private String baslik;
        private String yazar;
        private int yayinYili;
        private boolean oduncDurumu; // Kitabın ödünç alınıp alınmadığını saklar.

        public Kitap(String ISBN, String baslik, String yazar, int yayinYili) {
            this.ISBN = ISBN;
            this.baslik = baslik;
            this.yazar = yazar;
            this.yayinYili = yayinYili;
            this.oduncDurumu = false; // Varsayılan değer
        }

        // KAPSÜLLEME: Güvenli veri erişim metotları. (Getter/Setter)
        public String getISBN() { return ISBN; }
        public String getBaslik() { return baslik; }
        public boolean isOduncDurumu() { return oduncDurumu; }

        // KAPSÜLLEME: Sadece ödünç durumunu değiştirmeye izin veren kontrollü metot.
        public void setOduncDurumu(boolean oduncDurumu) {
            this.oduncDurumu = oduncDurumu;
        }

        // Object sınıfından miras alınan toString metodu ezildi (override). Nesnenin okunabilir çıktısını sağlar.
        @Override
        public String toString() {
            return "Kitap{" +
                    "ISBN='" + ISBN + '\'' +
                    ", Başlık='" + baslik + '\'' +
                    ", Yazar='" + yazar + '\'' +
                    ", Durum=" + (oduncDurumu ? "Ödünçte" : "Rafta") + // Kolay anlaşılır durum bilgisi
                    '}';
        }
    }


    // ----------------------------------------------------------------
    // 2. Soyut Üye Sınıfı (NYP: SOYUTLAMA ve KALITIM Temeli)
    // ----------------------------------------------------------------
    static abstract class Uye {
        // Bu alanlar tüm üye tipleri (Standart, Akademik vb.) için ortaktır.
        private int uyeNo;
        private String ad;
        private String soyad;
        private int maksimumOduncKitapSayisi;

        public Uye(int uyeNo, String ad, String soyad, int maksimumOduncKitapSayisi) {
            this.uyeNo = uyeNo;
            this.ad = ad;
            this.soyad = soyad;
            this.maksimumOduncKitapSayisi = maksimumOduncKitapSayisi;
        }

        // SOYUTLAMA PRENSİBİ: Soyut metot. Gerekli işlevi tanımlar (ne yapılacağını söyler),
        // ancak uygulamayı alt sınıflara bırakır (nasıl yapılacağını söylemez).
        // Bu, alt sınıfları kendi kural setlerini uygulamaya ZORLAR.
        public abstract boolean oduncAlmaKontrol(int mevcutOduncSayisi);

        // GETTER METOTLARI
        public int getUyeNo() { return uyeNo; }
        public String getAd() { return ad; }
        public String getSoyad() { return soyad; } // UyeSil metodunun ihtiyacı olan metot.
        public int getMaksimumOduncKitapSayisi() { return maksimumOduncKitapSayisi; }

        @Override
        public String toString() {
            return "Uye [No=" + uyeNo + ", AdSoyad=" + ad + " " + soyad +
                    ", Maks. Kitap=" + maksimumOduncKitapSayisi + "]";
        }
    }


    // ----------------------------------------------------------------
    // 3. Standart Üye Sınıfı (NYP: KALITIM ve ÇOK BİÇİMLİLİK)
    // ----------------------------------------------------------------
    static class StandartUye extends Uye {
        // KALITIM: 'extends Uye' ile tüm Uye özelliklerini miras alır.
        private static final int STANDART_MAKS_KITAP = 3;

        public StandartUye(int uyeNo, String ad, String soyad) {
            super(uyeNo, ad, soyad, STANDART_MAKS_KITAP); // Üst sınıfın kurucusunu çağırır.
        }

        // ÇOK BİÇİMLİLİK (Polymorphism): Soyut metot burada Standart Üye'ye özel kural (3 kitap) ile somutlaştırılır (override edilir).
        @Override
        public boolean oduncAlmaKontrol(int mevcutOduncSayisi) {
            if (mevcutOduncSayisi < getMaksimumOduncKitapSayisi()) {
                System.out.println("Kontrol Başarılı: Standart üye kuralı (Max 3) geçildi.");
                return true;
            } else {
                System.out.println("Kural İhlali: Standart üyeler en fazla " + getMaksimumOduncKitapSayisi() + " kitap alabilir.");
                return false;
            }
        }

        @Override
        public String toString() {
            // Üst sınıfın toString çıktısını kullanır ve başına tür bilgisini ekler.
            return "Standart " + super.toString();
        }
    }


    // ----------------------------------------------------------------
    // 4. Akademik Üye Sınıfı (NYP: KALITIM ve ÇOK BİÇİMLİLİK)
    // ----------------------------------------------------------------
    static class AkademikUye extends Uye {
        private static final int AKADEMIK_MAKS_KITAP = 5; // Farklı kural (5 kitap)

        public AkademikUye(int uyeNo, String ad, String soyad) {
            super(uyeNo, ad, soyad, AKADEMIK_MAKS_KITAP);
        }

        // ÇOK BİÇİMLİLİK: Aynı metot imzası, Akademik Üye için farklı bir kural (5 kitap) uygular.
        @Override
        public boolean oduncAlmaKontrol(int mevcutOduncSayisi) {
            if (mevcutOduncSayisi < getMaksimumOduncKitapSayisi()) {
                System.out.println("Kontrol Başarılı: Akademik üye kuralı (Max 5) geçildi.");
                return true;
            } else {
                System.out.println("Kural İhlali: Akademik üyeler en fazla " + getMaksimumOduncKitapSayisi() + " kitap alabilir.");
                return false;
            }
        }

        @Override
        public String toString() {
            return "Akademik " + super.toString();
        }
    }


    // ----------------------------------------------------------------
    // 5. Yönetim Sınıfı (Kutuphane) - Veri ve İş Akışı Yönetimi
    // ----------------------------------------------------------------
    static class Kutuphane {

        // Uygulamanın verilerini tutan koleksiyonlar (List)
        private List<Uye> uyeler = new ArrayList<>(); // Uye listesi, Kalıtım sayesinde her iki üye tipini tutabilir.
        private List<Kitap> kitaplar = new ArrayList<>();

        // Kitap Ekleme İşlemi
        public void kitapEkle(Kitap kitap) {
            kitaplar.add(kitap);
            System.out.println("BİLGİ: " + kitap.getBaslik() + " kütüphaneye eklendi.");
        }

        // Üye Ekleme İşlemi
        public void uyeEkle(Uye uye) {
            uyeler.add(uye);
            System.out.println("BİLGİ: " + uye.getAd() + " " + uye.getSoyad() + " sisteme eklendi. (Tür: " + uye.getClass().getSimpleName() + ")");
        }

        // Yardımcı Metotlar: Kitap ve Üye Bulma
        public Kitap kitapBul(String ISBN) {
            for (Kitap k : kitaplar) {
                // Kapsüllenmiş alana erişim için getISBN() metodu kullanılıyor.
                if (k.getISBN().equals(ISBN)) {
                    return k;
                }
            }
            return null;
        }

        public Uye uyeBul(int uyeNo) {
            for (Uye u : uyeler) {
                if (u.getUyeNo() == uyeNo) {
                    return u;
                }
            }
            return null;
        }

        public void kitapOduncAl(int uyeNo, String ISBN) {
            Uye uye = uyeBul(uyeNo);
            Kitap kitap = kitapBul(ISBN);

            if (uye == null) { System.out.println("HATA: Üye bulunamadı."); return; }
            if (kitap == null) { System.out.println("HATA: Kitap bulunamadı."); return; }
            if (kitap.isOduncDurumu()) { System.out.println("HATA: Kitap zaten ödünçte."); return; }

            // ÇOK BİÇİMLİLİK (POLİMORFİZM) PRENSİBİNİN KRİTİK UYGULAMASI:
            // uye.oduncAlmaKontrol() çağrısı, üyenin gerçek türüne (Standart/Akademik) göre farklı davranır.
            int mevcutOduncSayisi = 1; // Basitlik için varsayılan bir değer.
            if (uye.oduncAlmaKontrol(mevcutOduncSayisi)) {
                // Kapsülleme: Veri değişikliği sadece setter metodu ile yapılıyor.
                kitap.setOduncDurumu(true);
                System.out.println("BAŞARILI: " + uye.getAd() + " " + kitap.getBaslik() + " adlı kitabı ödünç aldı.");
            } else {
                System.out.println("İŞLEM BAŞARISIZ: Kural ihlali nedeniyle ödünç verme durduruldu.");
            }
        }

        public void kitapIadeEt(String ISBN) {
            Kitap kitap = kitapBul(ISBN);
            if (kitap == null || !kitap.isOduncDurumu()) {
                System.out.println("HATA: Kitap bulunamadı veya zaten raftadır. İade işlemi gerekmiyor.");
                return;
            }
            kitap.setOduncDurumu(false);
            System.out.println("BAŞARILI: " + kitap.getBaslik() + " adlı kitap iade edildi.");
        }

        // Ödevde istenen işlevlerden biri: Kitap Silme
        public void kitapSil(String ISBN) {
            Kitap kitap = kitapBul(ISBN);
            if (kitap != null && !kitap.isOduncDurumu()) {
                kitaplar.remove(kitap);
                System.out.println("BİLGİ: " + kitap.getBaslik() + " başarıyla silindi.");
            } else {
                System.out.println("HATA: Silinemedi. Kitap bulunamadı veya ödünçteyken silme işlemi yapılamaz.");
            }
        }

        // Ödevde istenen işlevlerden biri: Üye Silme
        public void uyeSil(int uyeNo) {
            Uye uye = uyeBul(uyeNo);
            if (uye != null) {
                uyeler.remove(uye);
                // Kapsülleme: getAd() ve getSoyad() metotları ile üye bilgileri okundu.
                System.out.println("BİLGİ: " + uye.getAd() + " " + uye.getSoyad() + " başarıyla silindi.");
            } else {
                System.out.println("HATA: Silinecek üye bulunamadı.");
            }
        }

        public void tumKitaplariListele() {
            if (kitaplar.isEmpty()) {
                System.out.println("Kütüphanede kayıtlı kitap bulunmamaktadır.");
                return;
            }
            System.out.println("\n--- Tüm Kitaplar (Kapsüllenmiş verilere erişim) ---");
            for (Kitap k : kitaplar) {
                System.out.println(k);
            }
        }

        public void tumUyeleriListele() {
            if (uyeler.isEmpty()) {
                System.out.println("Kütüphanede kayıtlı üye bulunmamaktadır.");
                return;
            }
            System.out.println("\n--- Tüm Üyeler (Farklı üye türleri tek listede) ---");
            for (Uye u : uyeler) {
                System.out.println(u); // Çok biçimli toString çağrısı
            }
        }
    }
}