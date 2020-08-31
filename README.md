# Android 3daysサマーインターン当日課題🏄
<img src="https://github.com/ikawashima-cq/Resource/blob/master/caraquri_logo_hz_cmyk%20(2).png" width="90%">

## 目次

<!-- TOC -->
- [課題について](#課題について)
- [プロジェクト概要](#プロジェクト概要)
- [予備知識](#予備知識)
<!-- /TOC -->

## 課題について

### 課題概要
からくり株式会社 Android 3dayサマーインターン(ハッカソン)の当日課題です。
下記の概要を詳しく読んだ上で課題を取り組んでください。

### 課題の取り組み方
- Githubにて`Use this template`を使用し、個人のリポジトリにコピー(publicにして下さい)して課題に取り組んでください。
コーディング規約:
- 標準的なコーディング規約であればどれに準じても良いですが、例として[参考リンク](
https://developer.android.com/kotlin/style-guide?hl=ja)を載せます。


## プロジェクト概要

### アプリ概要
Qiitaの記事を検索するアプリです。

### ビルド環境
- Android Studio: 4.0.1
- Kotlin: 1.4.0-release-Studio4.0-5
- minSdkVersion: 26 

### 導入ライブラリ
- [OkHttp](https://square.github.io/okhttp/#okhttp)
- [gson](https://github.com/google/gson)
- [Retrofit2](https://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)


### 動作確認機種
- お好きなエミュレーター、もしくは実機での確認を推奨しています。 

### ビルド方法
- 当日課題Repositoryのgit cloneをお願いします。

### PRを作成する上での注意点
- ローカルでビルドできている
- 動作確認までしている

### 当日までに調べておいて欲しい概念
- アクセス修飾子
- let関数の使い方
- RecyclerView
- 画面遷移
- ライフサイクル

## 予備知識

### GitFlow

- master
  - プロダクトとしてリリースするためのブランチ。リリースしたらタグ付けする。
- develop
  - 開発ブランチ。コードが安定し、リリース準備ができたら master へマージする。リリース前はこのブランチが最新バージョンとなる。
- feature
  - 機能の追加。 develop から分岐し、 develop にマージする。
- release
  - プロダクトリリースの準備。develop ブランチにリリース予定の機能やバグフィックスがほぼ反映した状態で develop から分岐する。 リリース準備が整ったら, master にマージし、タグをつける。次に develop にマージする。
- hotfix
  - リリース後のクリティカルなバグフィックスなど、 現在のプロダクトのバージョンに対する変更用。 master から分岐し、 master にマージし、タグをつける。次に develop にマージする。

<img src="https://user-images.githubusercontent.com/46508203/77295789-e4ec4e80-6d29-11ea-8608-1f24618d6b0f.png" width="500px">

- [git初心者への道 - お仕事で困らないレベルまでググっとします。 · GitHub](https://gist.github.com/yatemmma/6486028)
- [ Branchについて](https://havelog.ayumusato.com/develop/git/e513-git_branch_model.html)

### 開発をする上で知っておいてほしい基礎知識
- [http通信について](https://qiita.com/AkiyoshiOkano/items/ae4258c1caef3d9e70a2)
- [うまくメソッド名を付けるための参考情報](https://qiita.com/KeithYokoma/items/2193cf79ba76563e3db6)
- [英語のコメントや issue で頻出する略語の意味 (FYI, AFAIK, ...)](https://qiita.com/uasi/items/86c3a09d17792ab62dfe)
- [マークダウン記法 一覧表・チートシート](https://qiita.com/kamorits/items/6f342da395ad57468ae3)

### 開発で使うと便利なツール
項目 | ツール名
--- | ---
APIクライアント | Postman, Pawなど
HTTP通信キャプチャ | Charles
Gitクライアント |  Source Treeなど 

### 参考資料
- [Android developersの公式ドキュメント](https://developer.android.com/kotlin/first?hl=ja)
- [connpass API リファレンス](https://connpass.com/about/api)
- [リーダブルコード要約](https://qiita.com/AKB428/items/20e81ccc8d9998b5535d)
