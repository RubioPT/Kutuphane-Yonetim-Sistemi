# 📚 Nesneye Yönelik Programlama Projesi: Kütüphane Yönetim Sistemi

Bu proje, bir Kütüphane Yönetim Sistemi'nin Java dilinde, Nesneye Yönelik Programlama (NYP) ilkeleri (Kapsülleme, Kalıtım, Soyutlama, Çok Biçimlilik) kullanılarak geliştirilmiş konsol tabanlı bir simülasyonudur.

**Proje Amacı:** Nesneye Yönelik Programlama kavramlarının pratik uygulamasını göstermek.

---

## 🇹🇷 Proje Özeti (Türkçe)

### Temel Özellikler
* **Kitap Yönetimi:** Kitapları ISBN, Başlık, Yazar ve Yıl bilgileriyle ekleme, silme ve listeleme.
* **Üye Yönetimi:** Farklı üye türlerini (Standart Üye, Akademik Üye) ekleme ve silme.
* **Ödünç/İade İşlemleri:** Kitap ödünç alma ve iade süreçlerini yönetme.
* **Kural Kontrolü:** Üyelerin türüne göre (Çok Biçimlilik) ödünç alabileceği maksimum kitap sayısını kontrol etme.

### Uygulanan NYP Prensipleri

Projenin tüm yapısı, tek bir Java dosyasında (nested class yapısı kullanılarak) aşağıdaki dört temel prensibe uygun olarak tasarlanmıştır:

| Kavram | Uygulama Noktası | Açıklama |
| :--- | :--- | :--- |
| **Kapsülleme** | `Kitap` ve `Uye` Sınıfları | Tüm veri alanları (`private`) tanımlanmış, erişimler kontrollü `Getter/Setter` metotları ile sağlanmıştır. |
| **Soyutlama** | `Uye` Sınıfı | `abstract` sınıf yapısı kullanılarak, `oduncAlmaKontrol()` metodu soyut bırakılmış, böylece alt sınıfların kendi kural setlerini uygulaması zorunlu hale getirilmiştir. |
| **Kalıtım** | `StandartUye` ve `AkademikUye` Sınıfları | Bu sınıflar, ortak özellikleri miras almak için `Uye` sınıfından türetilmiştir (`extends`). |
| **Çok Biçimlilik (Polimorfizm)** | `kitapOduncAl()` Metodu | `Uye` referansı üzerinden çağrılan `oduncAlmaKontrol()` metodu, nesnenin gerçek türüne (Standart veya Akademik) göre farklı kural setini çalıştırır. |

### Nasıl Çalıştırılır?

Bu proje, Java SE Development Kit (JDK) gerektirir.
1.  `src/KutuphaneYonetimSistemi.java` dosyasını derleyin.
2.  Konsol/Terminal üzerinden çalıştırın. Uygulama, menü tabanlı bir arayüz ile açılacaktır.

---

## 🇬🇧 Project Summary (English)

### Key Features
* **Book Management:** Adding, deleting, and listing books with ISBN, Title, Author, and Year.
* **Member Management:** Adding and deleting different types of members (Standard Member, Academic Member).
* **Loan/Return Processes:** Managing the processes of lending and returning books.
* **Rule Validation:** Controlling the maximum number of books members can borrow based on their type (Polymorphism).

### Applied OOP Principles

The entire project structure is implemented within a single Java file (using a nested class structure) adhering to the following four fundamental principles:

| Principle | Application Point | Description |
| :--- | :--- | :--- |
| **Encapsulation** | `Kitap` and `Uye` Classes | All data fields are defined as `private`, and access is provided via controlled `Getter/Setter` methods. |
| **Abstraction** | `Uye` Class | An `abstract` class structure is used, leaving the `oduncAlmaKontrol()` method abstract, thereby forcing subclasses to implement their own set of rules. |
| **Inheritance** | `StandartUye` and `AkademikUye` Classes | These classes inherit common properties from the base `Uye` class using `extends`. |
| **Polymorphism** | `kitapOduncAl()` Method | The `oduncAlmaKontrol()` method, called via the `Uye` reference, executes a different rule set based on the object's actual type (Standard or Academic). |

### How to Run?

This project requires the Java SE Development Kit (JDK).
1.  Compile the `src/KutuphaneYonetimSistemi.java` file.
2.  Run the application from the Console/Terminal. The application will launch with a menu-driven interface.

---

### 📦 Teslimat Dosyaları (Delivery Files)

Ödev kurallarına göre, final teslimatınız aşağıdaki ögeleri içermelidir:
* `proje_takimi.txt` (Grup üyeleri ve görevleri)
* **Bu GitHub Depo Linki** (Halka açık olmalıdır)
* Proje Sunum Videosu (Maksimum 10 dakika)
