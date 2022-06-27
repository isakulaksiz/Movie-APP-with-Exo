# BeinConnectClone - Movie App
<p align="center">
 <img src="https://play-lh.googleusercontent.com/ojJC2ML7PlN29TPWreEtJOqmKBktv43ZBo9y399K17TvdujxgoI20jxTFE9xla-_lto">
</p>
<p align="center">
<img width="326" alt="1" src="https://user-images.githubusercontent.com/26628508/175878520-0974d880-c1f4-4e8e-92ba-723af38df33b.png">
<img width="326" alt="2" src="https://user-images.githubusercontent.com/26628508/175878703-dc9ff61f-e502-485d-93e6-e0dda13bcdf2.png">
<img width="326" alt="3" src="https://user-images.githubusercontent.com/26628508/175878793-7c9a2f68-c7aa-4143-9aa5-fb1fcbaf10c6.png">
<img width="326" alt="4" src="https://user-images.githubusercontent.com/26628508/175878857-5016d737-cde3-4adf-a125-47b1c9e892b6.png">
<img width="326" alt="5" src="https://user-images.githubusercontent.com/26628508/175878928-b8fc9d28-7db1-4853-82e5-a98d76482332.png">
</p>

- [TR]: OMDB API kullanılarak kullanıcının OMDB platformunda yer alan Film gibi içeriklere BeinConnect kullanıcı arayüzüyle ulaşması sağlanmaktadır.
- [EN]: By using the OMDB API, the user is able to access the content such as movies on the OMDB platform with the BeinConnect user interface.


## Özellikler / Features

- [TR]: Ana Ekran, İçerik Detay Ekranı, Arama Ekranı
- - [TR]: Ana Ekran :
    - [TR]: Ürünler Recyclerview kullanarak gösterildi.
    - [TR]: Slide ekranında ilgili Posterler gösterildi.
- [TR]: Arama ekranında :
    - [TR]: Search bar ve sonuçların grid yapısında listelenmiştir.
- [TR]: İçerik Detay Ekranında :
    - [TR]: Arama sonucunda gelen değerlere göre detay ekranı üzerinde çalışılıyor.
    - [TR]:ExoPlayer kullanılarak video oynatıcısı eklendi

------
- [EN]: Main Screen, Content Detail Screen, Search Screen
- - [EN]: Main Screen :
    - [EN]: Products were displayed using Recyclerview.
    - [EN]: Related Posters are displayed on the slide screen.
- [EN]: On the search screen:
    - [TR]: Search bar and results are listed in grid structure.
- [TR]: On Content Detail Screen:
    - [TR]: Working on the detail screen according to the values obtained as a result of the search.
    - [EN]: Added video player using ExoPlayer

## Tech
* Mimari / Architectural Design
    - MVVM
    - Lifecycle
    - Navigation
    - ViewModel
* 3.Parti Kütüphaneler / 3rd Party Libraries
    - Retrofit 2
    - GSON
    - RxJava [Async]
    - Glide [Img Loading like Picasso etc.]
    - ExoPlayer [Media Player]


## JSON
```sh
https://api.themoviedb.org/3/discover/movie?api_key=92b975410b217a6ca13099b35bf4be46&page=1
```

```json
{
	"page": 1,
	"results": [
		{
			"adult": false,
			"backdrop_path": "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
			"genre_ids": [
				28,
				12,
				878
			],
			"id": 634649,
			"original_language": "en",
			"original_title": "Spider-Man: No Way Home",
			"overview": "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
			"popularity": 2838.414,
			"poster_path": "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
			"release_date": "2021-12-15",
			"title": "Spider-Man: No Way Home",
			"video": false,
			"vote_average": 8.1,
			"vote_count": 13748
		}
	],
	"total_pages": 1,
	"total_results": 1
}
```


#### İletişim / Contact

```sh
mail: isakulaksiz@outlook.com
```
