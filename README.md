# FTC Start Planer 🤖📋

**FTC Start Planer**, FTC (FIRST Tech Challenge) takımlarının **saha stratejileri** geliştirmelerini, analiz etmelerini ve organize bir şekilde kaydetmelerini sağlayan bir Android uygulamasıdır.

## 🎯 Uygulamanın Amacı

FTC Start Planer, takımların maç öncesi ve maç içi saha planlarını hızlı ve verimli bir şekilde oluşturmasına yardımcı olur. Strateji konuşmalarını unutulmaktan kurtarır, sezona dair taktiksel bir arşiv oluşturur.

## 🧠 Özellikler

- 📌 Strateji oluşturma ve başlıklandırma  
- 🧩 Maçlara özel planlama yapabilme  
- 🔄 Hazırlanan stratejileri kolayca güncelleme ve silme  
- 📚 Geçmiş stratejilere erişim ve karşılaştırma  
- 🧑‍🤝‍🧑 Takım içi strateji paylaşımlarını destekleme (gelecek sürümlerde)

## 🛠 Kurulum (Android Studio ile)

1. Bu projeyi klonla:

   ```bash
   git clone https://github.com/HamzaGurbuz/FTCStartPlaner.git
   ```

2. Android Studio ile aç:
   - `File > Open` yolunu izleyerek projeyi seç.
   - Gradle senkronizasyonunun tamamlanmasını bekle.

3. Uygulamayı emülatör ya da Android cihazda başlat:

   ```bash
   Run > Run 'app'
   ```

## 📁 Proje Yapısı

```
FTCStartPlaner/
├── app/
│   ├── java/com/example/ftcstartplaner/
│   │   ├── MainActivity.java
│   │   ├── StrategyModel.java
│   │   ├── StrategyAdapter.java
│   │   └── ...
│   └── res/
│       ├── layout/
│       ├── values/
│       └── drawable/
├── build.gradle
└── AndroidManifest.xml
```

## ✅ Gereksinimler

- Android Studio (Arctic Fox ve üzeri önerilir)
- Java 8 veya üstü
- Minimum SDK: API 21 (Android 5.0)

## 🔮 Gelecek Planları (TODO)

- [ ] Stratejileri dışa aktarma (PDF/JSON)
- [ ] Bulut senkronizasyonu
- [ ] Takım üyeleri arasında paylaşım
- [ ] Önceden tanımlı saha şablonları

## 🤝 Katkıda Bulunmak

Katkılara açığız! Aşağıdaki adımlarla katkı sağlayabilirsin:

```bash
git checkout -b yeni-özellik
git commit -m "Yeni özellik eklendi"
git push origin yeni-özellik
```

Sonrasında bir Pull Request (PR) açman yeterli!


## 👨‍💻 Geliştirici

**Hamza Gürbüz**  
FTC ve FRC topluluğuna katkı sağlayan bir yazılımcı ve mentör  
🔗 GitHub: [@HamzaGurbuz](https://github.com/HamzaGurbuz)

---

> ⚙️ *İyi bir strateji, iyi bir robot kadar kritiktir. FTC Start Planer ile stratejiniz cebinizde!* 📲
