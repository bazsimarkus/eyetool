# EyeTool
![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/eyetool_logo.jpg)

**EyeTool is a universal distance measuring tool for Android devices that can be used primarily when ordering glasses online. Many people cannot afford to buy eyeglasses or have their lenses polished by an optician. Opticians work at high margins with a small number of products, practically manufactory-like, so the final price of glasses will be high. To compensate for this, online eyewear ordering sites (eg. SelectSpecs, Firmoo) have appeared in recent years, where by specifying a few parameters one can get a cheap eyewear. Like in a webshop, the customer can choose the glasses they like, then after providing a few parameters you can order them, make them cheap and mail them to them. Such online ordered glasses cost around $20-30 compared to $200-300+ purchased in conventional optics.**

The parameters to be specified are usually the diopter, which are available free of charge from a CTC ophthalmologist, and the so-called pupillary distance (PD), which has the purpose of aligning the two focal points of the lenses, in line of sight, so that the glasses will improve sight in the desired way, they will comfortably sit on the head, will not snap, nor will they be too big.
EyeTool is used to determine the PD parameter easily, without the help of an optician.

*Simplicity, intuition and instant, quick usability were the guiding principles during the development of the application, as many older and visually impaired people will use the application. In addition, it was a requirement that the app should run and perform basic functions smoothly and functionally on many legacy devices as well.*

## Main functions

To determine this PD value, the user creates a selfie with the phone's built-in camera, holding a standard size card - student card, credit card, etc. to the forehead.
The application guides the user through tutorial screens before capturing the image, informing the user of the correct image acquisition process.
The application then creates a "new measurement project" in which the user "annotates" the image, that is, by touching and dragging, places markers on his eyes and on the lower corners of the card.
From the given inputs, the application calculates the pupillary distance using a mathematical formula, then the result is presented to the user on a Result Screen.
The user can share the measurement result with other applications, copy it to the clipboard, share it on social platforms or with the eyewear webshop that redirected him to the application.
The user can save the measurement result for later use. This result will be available directly on the homepage.

## Using the application

When the app starts up, the welcome screen will greet us with two buttons:

 - A "Get Started" button to start the measurement procedure
 - A "See saved PD" where the user can save his / her PD, so once it has been measured, it won't have to be remeasured later, even if it has been forgotten.

There are white text bubbles on each screen, that contain the actual task, so the user is never lost.
When clicking on the application logo some developer info will show up.

|![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/1.jpg)  |![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/2.jpg)  |
|--|--|
| The home screen with the "Get started" button and the "See saved PD" button. | The home screen during the recall of the saved PD. |

Before the measurement process, the application explains the measurement process on four tutorial screens. Every step is described in textual form as well as in graphic form. All of these screens have a "Done" button except for the last one, on which there is a "Take photo" button. After pushing this button, the built-in camera application opens.
With the phone's camera the user can take the photo required for the measurement. 

|![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/3.jpg)  | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/4.jpg) | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/5.jpg) | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/6.jpg) | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/7.jpg) |
|--|--|--|--|--|

Returning from the camera, we can check whether the image we took is correct and, if necessary, we can take a new picture or approve the photo. If no picture is taken, an "X" will appear in the place of the picture and the application will give a warning. In this case, we cannot go further until we successfully take a picture.
| ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/8.jpg) |![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/9.jpg)  |
|--|--|
| Approval screen of the taken photo  | Warning of the approval screen when no photo has been taken, in this case the "Continue" button is deactivated |

After approval, we have to "annotate" the image by placing markers first on the pupils and then on the bottom two corners of the card as shown on the images. To move the markers, tap them, then drag and drop them to place them. If they are in place, confirm the annotation with the green "Done" button.
| ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/10.jpg) | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/11.jpg) | ![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/12.jpg) |
|--|--|--|

After annotation, the phone calculates the result and shows it on the Result Screen. By clicking on the "Save Result" button on this screen, you can save the result so that it can be recalled on the home screen. The "Share Result" button can be used to share the result, and the "Back to home" button to return to the home screen without saving.

![enter image description here](https://github.com/bazsimarkus/EyeTool/raw/master/docs/13.jpg)

## Used tools and technologies

 - Android Camera API for capturing and returning images as Bitmap (URI, External Storage Persistent Photo Storage)
 - Runtime permissions to use the camera at a higher API level
 - SharedPreferences Activities to permanently save measurement results
 - Android Send Intent for social sharing
 - Translucent statusbar in every Activity, depending on the API
 - Touch-based, multilayer ImageView drawing
 - Intuitive UX experience, universal RelativeLayout on all devices

## Difficulties during development
My biggest problem was with older smartphones, especially with Samsung ones. When tested on these devices, the app ran out of heap very quickly, leading to crashes in the application. This was solved by finishing unused Activities and better memory management. (I also set heap-large in the Manifest file) There were also problems with different sizes of photos and UIs on screens of different sizes, which I could eliminate with RelativeLayout. The Intents are only capable of transferring up to 500KB, so I had to solve the storage of photos in External Storage, and then the URI of the images are transferred to the Intents.
