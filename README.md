# NoteLocation

## 系統環境

  -- Android Studio 3.0.1  
  -- minSdkVersion 19  
  -- targetSdkVersion 26
  

## 功能介紹

主要功能是透過地理位置來提醒用戶待辦事宜

一種以鬧鐘為變形的提醒方式

判斷的依據由時間轉換為地點

例如計畫經過朋友家附近時要順便去拿東西

將待辦事項輸入後

這樣就可以透過地理感知提醒使用者待辦事項

讓事情不再推遲延後，也省掉事後想起來的懊悔。

## 進度說明

目前版本已經具備基礎的記事功能
可對記事進行新增、刪除與修改

位置感知功能仍在開發中 Branch: feature/GPSFunction


## 架構說明

### Note  
  -- 封裝記事的資訊，如標題、地點、內容與經緯度等  
  -- !! Note 為 NoteBook 的內部物件 !!
  
### NoteBook  
  -- 用來管理Note物件，並提供搜尋、新增、修改與刪除Note物件介面
  
### DBHelper  
  -- SQLite資料庫控制

### NoteRecyclerViewAdapter
  -- MainList ( Activity ) 中 RecyclerView 的 Adapter
  
### MainList ( Activity )  
  -- 為APP的初始畫面，向NoteBook撈取Note物件，並將所有的Note物件以列表的方式呈現
  
  清單內容提供下列資訊
  * 記事標題  
  * 記事地點
  * 記事新增或修改的日期  
  * 位置功能狀態 -- !! 施工中 !!  

  該頁面提供下列操作  
  * 檢視Note切換Activity進入 NoteInfo ( Activity ) -- 顯示該Note各項資訊  
  * 新增Note切換Activity進入 NoteEdit ( Activity ) -- 欄位空白  
  * 修改Note切換Activity進入 NoteEdit ( Activity ) -- 欄位顯示該Note各項資訊  
  * 刪除Note  
  * Toolbar清單提供About資訊 -- 顯示作者名
  
### NoteInfo ( Activity )
  -- 呈現特定Note物件資訊
  頁面呈現下列內容
  * 記事標題
  * 記事地點
  * 新增或修改日期
  * 位置功能狀態  -- !! 施工中 !! 
  * 記事內容
  該頁面提供下列操作  
  * Toolbar提供編輯按鈕切換Activity進入 NoteEdit ( Activity ) -- 欄位顯示該Note各項資訊
  * Toolbar提供取消按鈕結束目前的NoteInfo ( Activity ) 頁面

### NoteEdit ( Activity )
  -- 頁面提供欄位給使用者填寫Note相關資訊
  
  目前提供下列欄位
  * 標題
  * 地點
  * 註記內容
  
  該頁面提供下列操作  
  * Toolbar提供完成按鈕將資訊儲存，並結束目前的NoteEdit ( Activity ) 頁面  
  * Toolbar提供取消按鈕結束目前的NoteEdit ( Activity ) 頁面  

## 位置感知功能

Branch: feature/GPSFunction

本功能正在開發中，預計達成目標如下

1. 進入Map畫面時，鏡頭需要拉到裝置目前位置

2. 點擊畫面設定Mark標籤

3. 儲存設定之Mark標籤經緯度

4. 可根據該記事之經緯度進行判斷，當裝置進入指定經緯度時須跳出通知或提示

5. 提供開啟或關閉地理感知功能


## 額外功能

1. 實作記事地圖的顯示方式 ( 有別於原本 MainList 列表的呈現方式 )



