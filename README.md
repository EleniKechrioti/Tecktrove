# Application for a Personal Computer composition

## [***Software requirements in text format***](docs/markdown/software-requirements.md) in text format

The two stakeholders of our system, the direct users, are the customers and employers of the business.

The company's staff will have the possibility to modify the list of components, adding, removing and changing the characteristics of the products (price, name, etc.) as well as the offered required ports for their operation.

Customers will be able to search for individual products and formulations as well as learn related information about them. They will also be able to create their own PC compositions, save them for future purchase, publish them and evaluate the compositions of others. Finally, customers can add individual parts and assemblies to their shopping cart and complete the purchase by filling in the billing information on the relevant form.

In order for the composition storage process to be implemented, the system should store as well as retrieve the storage information for each personalized user. To implement the publication and evaluation of compositions, the system should store and update the list of components appropriately. Also, to make the purchase, the system should check if the composition meets the compatibility and completeness conditions. Otherwise it will prompt the user to modify their composition.

[***Assumptions made regarding the above requirements***](docs/markdown/english/software-requirements.md/#Assumptions-and-dependencies)

## ![Use Case Diagram](docs/markdown/uml/requirements/use-case-diagram.png)

## Overview
The application's source code and assets are all under the app folder. The documentation for the app, i.e the descriptions of the use cases, some activity and sequence diagrams the domain model and the class diagram are all under the docs folder.

You can find the software requirement analysis where all of the above are mentioned and organized in sections in 
[greek](https://github.com/EleniKechrioti/Tecktrove/blob/main/docs/markdown/greek/software-requirements.md) and in 
[english](https://github.com/EleniKechrioti/Tecktrove/blob/main/docs/markdown/english/software-requirements.md).

Also you can see in the application-planning how we planned our application using the View, Presenter, Activity model in the class diagrams, some sequence diagrams of the most important methods and the coverage in testing of our application.
[application-planning in greek](https:/Mak/github.com/EleniKechrioti/Tecktrove/blob/main/docs/markdown/greek/application-planning.md) and [application-planning in english](https://github.com/EleniKechrioti/Tecktrove/blob/main/docs/markdown/english/application-planning.md).

## Features
- User authentication, log in and registration.
- Search for products and computer compositions.
- See the product's details.
- Rate a computer you have already bought.
- Make your own personal computer and save it.
- Buy products.
- Publish the computer you made.
- Manage your account.
- And specifically for employers, add/delete products, add quantity and edit product information.

## How to get the app

  1. Clone the repository.
  2. Open Android Studio and import the project
  3. Connect your phone (wifi/usb) or create a stimulator.
  4. Run the app.

If everything is okay the app will show up in your phone in the starting screen.
You can either register with your own credentials or you can use:
- Username: george, Password: ok123456 for customer
- Username: eleni3, Password: elen!562 for employer

This were made for test purposes. You can find the rest user credentials by yourself if you know 😝.

## Collaborators
- [EleniKechrioti](https://github.com/EleniKechrioti)
- [Maria Schoinaki](https://github.com/MariaSchoinaki)
- [Christos Stamoulos](https://github.com/ChristosStamoulos)

## Convertion of Umlet diagrams
The conversion to an image of the Umlet diagrams placed in the docs/uml folder is done by executing the commands:
```bash
# linux
cd docs
./mvnw umlet:convert
```
```bash
# windows
cd docs
mvnw umlet:convert
```
A prerequisite is to initialize the JAVA_HOME environment variable with the location of the Java JDK.
