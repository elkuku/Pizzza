## Pizzza

The Pizzza project tries to connect an Android App with a Joomla! CMS site. This is done by a REST interface.

The goal is to retrieve data and store it in the device for offline reading.

The information can be updated and it is also possible to implement authentication.

The project consists in several parts:

### Android Application
The application uses fragments and has layouts for portait and landscape devices.

Language files are provided for english, german and spanish. It should also be possible to retrieve localized content from the Joomla! site by adding a language code with the devices current language (not implemented yet).

### REST Plugins
Plugins for the Joomla! CMS to work in conjunction with the [GetJ2Rest](https://github.com/elkuku/GetJ2Rest) project that is included as a submodule.

Currently the following content types are supported:

* The [SobiPro extension](http://sigsiu.net)
* Joomla! core com_content
* Joomla! core com_contact

The plugins don't make any use of the components code (models, tables, etc.) and just use very basic queries, bypassing any ACL rules, publishing options, sorting or other component settings.

### Joomla! component
The component is used to manage different access types and ACL (WIP).

### Joomla! CMS Template
The template is based on [Twitters bootstrap](http://twitter.github.com/bootstrap/).

### Screenshots

#### Catalog
![](http://i.imgur.com/rTlqa.png)

![](http://i.imgur.com/qmXpC.png)![](http://i.imgur.com/Xzg4f.png)
The "Catalog" is provided by a Sobi section with categories and items. The fields are predefined and have to be adjusted to your needs.

The head line "green on yellow" is a daily bargain acording to the current weekday (see the next section).

#### Promocions
![](http://i.imgur.com/axeqH.png)

![](http://i.imgur.com/2ssOI.png)
The "Promocions" are meant to be daily bargains. They are provided by a Sobi section without categories.

#### News
![](http://i.imgur.com/NjdZZ.png)

![](http://i.imgur.com/cSjVR.png)
The "News" are provided by com_content. Only The Titel, text and creation date are shown.

#### Orders
![](http://i.imgur.com/8fDam.png)

![](http://i.imgur.com/5XRML.png)
The Orders section is an address taken from a com_contact item.
