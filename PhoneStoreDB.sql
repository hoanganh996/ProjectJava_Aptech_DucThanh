create database PhoneStoreDB;

USE PhoneStoreDB;
go

create table Catalog(
	CatalogId int primary key not null,
	CatalogName nvarchar(200)  null,
	Descriptions nvarchar(100) null,
	ParentId int null,
	Status bit null ,
	Images nvarchar(100) null,
	DisplayNumber int null
)

Create table Product(
	ProductId int primary key not null,
	ProductName nvarchar(100) null,
	ProductContent ntext null,
	ProductContentDetail ntext null,
	Images nvarchar(100) null,
	PriceInput float null,
	PriceOutput float null,
	Quantity int null,
	Created date null,
	Status bit null,
	Discount int null,
	CatalogId int foreign key (CatalogId) references Catalog ,
	SupplierId int foreign key (SupplierId) references Supplier
)

Create table ImageLink(
	ImageLinkId int primary key not null,
	ImageLinkName nvarchar(100) null,
	ProductId int foreign key(ProductId) references Product,
	Status bit null
)

Create table Users(
	UserId int not null primary key,
	UserName nvarchar(100) not null,
	Password nvarchar(100) not null,
	Mail nvarchar(50) null,
	Phone varchar(15) null,
	Address nvarchar(100) not null,
	GroupId int foreign key(GroupId) references Groups,
	Status bit null,
	Created date null,
	)

create table Orders(
	OrderId int primary key not null,
	OrderName nvarchar(50) null,
	OrderNumber varchar(50) null,
	Phone varchar(15) null,
	Email nvarchar(100) null,
	Address nvarchar(100) null,
	TotalAmount float not null,
	PaymentDate Date null,
	Created datetime not null,
	OrderStatus int null, 
	UserId int null,
	PaymentMethod nvarchar(100) null
)

Create table OrderDetail(
	OrderDetailId int not null primary key identity,
	ProductId int foreign key (ProductId) references Product,
	OrderId int foreign key (OrderId) references Orders,
	Amount float null,
	Price float null,
	Quantity int null,
	Status int null
)

Create table PaymentType(
	PaymentTypeId int primary key not null,
	PaymentTypeName nvarchar(100) null,
	Descriptions nvarchar(100),
	Status bit null,
)

Create table Functions(
	FunctionId int primary key not null ,
	FunctionName nvarchar(100) null,
	FunctionStatus bit null
)

Create table Groups(
	GroupId int primary key not null,
	GroupName nvarchar(100) null,
	Status bit null
)

	Create table Supplier(
	SupplierId int primary key not null,
	SupplierName nvarchar(100) null,
	SupplierPhone varchar(15) null,
	SupplierMail nvarchar(100) null,
	SupplierAddress nvarchar(100) null,
	Status bit null
)

Create table Feedback(
	FeedbackId int not null,
	Name nvarchar(100) null,
	Email nvarchar(100)null,
	Content ntext null,
	Status bit null
)

 Create table ProductDetail(
	ProductDetailId int primary key not null,
	ProductId int foreign key (ProductId) references Product,
	Status bit null,
	Screen nvarchar(20) null,
	Battery int null,
	Capacity nvarchar(20) null,
	OperatingSystem nvarchar(50) null,
	RAM nvarchar(10)
	)

	Create procedure getAllCatalog(
	@
	)