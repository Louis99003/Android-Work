
# Android Team 實作題


## API使用 台北市資料平台 Open Data
-  **[臺北市立動物園_館區簡介](https://data.taipei/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2)**
-  **[臺北市立動物園_動物資料](https://data.taipei/dataset/detail?id=5cb73231-b741-48b3-bec3-2ef57bb10029)**

## 基本功能

- 顯示動物園館區料表列表資料
- 顯示動物園館區詳細資料與該館區相關動物列表
- 顯示動物詳細資料

## 專案說明

- 工時約18小時
- 使用語言為Kotlin
- 運用Coroutine+Flow與LiveData建構MVVM架構


## 實作遇到的問題

- **API資料中的關鍵字查詢功能為失效狀態**  
  --> 將寫信給該API機構通知該狀況

- **API中大部分回傳的圖片網址都為http且無法顯示，但將其改成https卻能顯示圖片，目前認爲問題為該圖片網址http port為失效狀態**  
  --> 將寫信給該API機構並說明狀況，並建議修復http port或是將回傳的圖片網址皆改成https


## 特別設計

- Data Model層有區分『API回傳的Model』與『View要顯示的Model』，會這樣做的原因是以前有寫過網頁，sql 資料model與要顯示給畫面或組成API資料的model也是分開的，回來寫APP後就沿用此設計。特別的提一下，後來有遇到API規格還沒好(甚至後端都還沒動)但卻要APP先做的專案，這個設計就有讓API設計好的時候不用改太多。

## 額外補充

- 面試官有詢問一個開放性問題，「**假設後端無法立即修復關鍵字搜尋錯誤，且不能將所有資料 built-in 到 App，還能有什麼方式能嘗試達到相同功能？**」  
  -> 該問題目前有想到解法為，App端在MVVM中的Repository或是ViewModel層，將資料篩選過後再傳遞給View顯示

## 感謝

感謝正在閱讀此份文件的各位，若有任何錯誤與問題，請讓我知道，並讓我從錯誤中學習，謝謝