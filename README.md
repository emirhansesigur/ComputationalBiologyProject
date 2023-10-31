# Hesaplamalı Biyoloji Dönem Projesi

## Bu program ne işe yarıyor
***Bursa Teknik Üniversitesi Bilgisayar Mühendisliğinde*** 1. sınıfın Bahar Döneminde almış olduğum ***Hesaplamalı Biyoloji*** dersinde yaptığım projeyi sizlerle yaplaşmaktan mutluluk duyuyorum.

***DNA dizilimi hizalama (layout oluşturma)*** sürecini gerçekleştiren bir bilgisayar programı yazdım.

Programımın aşağıdaki özellikleri sağlamaktadır:

•	Sekanslanacak olan DNA dizilimi ya klavyeden girilir ya da program tarafından kullanıcının istediği uzunlukta bir DNA dizilimini rastgele bazlar üreterek oluşturulur.

•	Program, kullanıcıya kaç adet sekans istediğini (N sayısı) ve her sekansın uzunluğunu (L sayısı) sorar.

•	N ve L değerlerine göre program, rastgele sekanslar üretir ve bu sekansları tümleyenleri ile ekranda gösterir.

•	Program, N adet sekansı ve bu sekansların tümleyenlerini ***8.1 numaralı fotoğrafta*** yer alan algoritmadan faydalanarak eşleştirmeye çalışır ve her sekans ikilisi için bir eşleşme skoru hesaplar. Algoritmada kullanılacak olan eşleşme ödülü/eşleşmeme cezası ve indel cezası programın başında kullanıcıdan pozitif sayılar olarak alınır.
![1.photo](https://github.com/emirhansesigur/ComputationalBiologyProject/blob/main/hb.PNG?raw=true)

•	Sekansların eşleşme skorları N^2 boyutlu bir matrise kaydedilir.

•	Bu matrisin bir örneği altta yer alan fotoğrafta görülmektedir. Matrisin simetrik değildir. Bu matriste kabul edilebilecek olan en küçük skor (T değeri) de önceden kullanıcıdan alınır. Matris buna göre doldurulur ve satır/sütun indisleriyle beraber bir dosyaya yazılır.

•	Son olarak ***şekil 8.3***'ün altında anlatılan layout oluşturma süreci gerçekleştirilir. Bunun için matristeki en yüksek skorlu hücrelerden başlanır ve bu hücreleri temsil eden sekanslarla yeni sekanslar hizalanarak layout'lar yukarı ve aşağı yönlü olarak büyütülür. Tespit edilen layout'ların her biri yine bir dosyaya yazılır. Layout tespit işlemi, kullanılmamış sekans kalmayana kadar devam ettirilir.
![2.photo](https://github.com/emirhansesigur/ComputationalBiologyProject/blob/main/hb2.PNG?raw=true)
