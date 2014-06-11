<<<<<<< HEAD
package com.example.kitchapp;


	public class ItemRecipeWithImage {

		private String image;
		private int imageId;
		private String title;
		//private String url;

		public ItemRecipeWithImage() {
			super();
		}

		//public ItemRecipeWithImage(int image, String title, String url) {
		public ItemRecipeWithImage(String image, String title) {
			super();
			this.image = image;
			this.title = title;
			//this.url = url;
		}

		public ItemRecipeWithImage(int image, String title) {
			super();
			this.imageId = image;
			this.title = title;
			//this.url = url;
		}
		public String getImage() {
			return image;
		}

		public int getImageId(){
			return imageId;
		}
		public void setImage(String image) {
			this.image = image;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		/*public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}*/

	}


=======
package com.example.kitchapp;


	public class ItemRecipeWithImage {

		private String image;
		private int imageId;
		private String title;
		//private String url;

		public ItemRecipeWithImage() {
			super();
		}

		//public ItemRecipeWithImage(int image, String title, String url) {
		public ItemRecipeWithImage(String image, String title) {
			super();
			this.image = image;
			this.title = title;
			//this.url = url;
		}

		public ItemRecipeWithImage(int image, String title) {
			super();
			this.imageId = image;
			this.title = title;
			//this.url = url;
		}
		public String getImage() {
			return image;
		}

		public int getImageId(){
			return imageId;
		}
		public void setImage(String image) {
			this.image = image;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		/*public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}*/

	}


>>>>>>> Rama-Mayra_Android
