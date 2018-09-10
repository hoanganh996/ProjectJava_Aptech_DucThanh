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

	//Procedure
CREATE proc getAllCatalog
 as
 begin
 Select * from Catalog where Status = 'True'
 end
 go

 CREATE proc getCatalogRoot
as
begin
Select top 9 * from Catalog where ParentId = 0 and Status= 'True' order by DisplayNumber asc
end
go

CREATE proc insertCatalog
@name nvarchar(100),
@des nvarchar(100),
@pare int = 0,
@img nvarchar(100),
@dis int

as
begin
if @pare != 0
	insert into Catalog(CatalogName,Descriptions,ParentId,Images,DisplayNumber, Status) values(@name,@des,@pare,@img,@dis,'True')
else
   insert into Catalog(CatalogName,Descriptions,ParentId,Images,DisplayNumber,Status) values(@name,@des,0,@img,@dis,'True')
end
go

create proc getCatalogById
@id int
as
begin
Select * from Catalog where CatalogId = @id
end
go

CREATE proc updateCatalog
@id int,
@name nvarchar(100),
@des nvarchar(100),
@pare int,
@img nvarchar(100),
@dis int,
@status bit
as
begin
update Catalog set CatalogName = @name,
				   Descriptions = @des,
				   ParentId = @pare,
				   Images = @img,
				   DisplayNumber = @dis,
				   Status = @status
			   where CatalogId = @id
end
go

create proc deleteCatalog
@id int
as
begin
update Catalog set Status = 'False'
			   where CatalogId = @id
end
go

CREATE proc insertFeedback
@name nvarchar(100),
@email nvarchar(100),
@content ntext
as
begin
Insert into Feedback(Name,Email,Content,Status) 
values(@name,@email,@content,'False')
end
go

create proc getAllFeedback
as
begin
Select * from Feedback order by FeedbackId desc
end
go

create proc totalFeedback
@total int output
as
begin
select @total = count(FeedbackId) from Feedback where Status ='False'
end
go

create proc updateFeedback
@id int
as
begin
Update Feedback set   Status = 1
				where FeedbackId = @id
				end
go

create proc getAllFunction
as
begin
Select * from Functions where Status ='True'
end
go

create proc getFunctionById
@id int
as
begin
Select * from Functions where FunctionId = @id
end
go

create proc insertFunction
@name nvarchar(100)
as
begin
insert into Functions(FunctionName,Status) values (@name,'True')
end
go

create proc updateFunction
@id int,
@name nvarchar(100),
@status bit
as
begin
Update Functions set FunctionName = @name,
				  Status = @status
				  where FunctionId = @id
end
go

create proc deleteFunction
@id int
as
begin 
update Functions set Status = 'False' where FunctionId = @id
end
go

create proc checkFunctionName
@name nvarchar(100),
@re bit output
as
begin
   if EXISTS(SELECT @name FROM Functions where FunctionName = @name)
      set @re = 'True'
   else 
      set @re = 'False'
end
go

create proc checkExistFunctionName
@id int,
@name nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @name FROM Functions where FunctionName = @name and FunctionId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

create proc getAllGroup
as
begin
Select * from Groups where Status ='True'
end
go

create proc getGroupById
@id int
as
begin
Select * from Groups where GroupId = @id
end
go

create proc insertGroup
@name nvarchar(100)
as
begin
Insert into Groups(GroupName, Status) values(@name,'True')
end
go

create proc updateGroup
@id int,
@name nvarchar(100),
@status bit
as
begin
update Groups set GroupName = @name,
				  Status = @status
			  where GroupId = @id
			  end
go

create proc deleteGroup
@id int
as
begin
update Groups set  Status = 'False'
			  where GroupId = @id
end
go

create proc checkGroupName
@name nvarchar(100),
@re bit output
as
begin
   if EXISTS(SELECT @name FROM Groups where GroupName = @name)
      set @re = 'True'
   else 
      set @re = 'False'
end
go

create proc checkExistsGroupName
@id int,
@name nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @name FROM Groups where GroupName = @name and GroupId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

CREATE proc getAllOrder
as
begin
Select * from Orders order by OrderId desc
end
go

CREATE proc getOrderByUserId
@id int
as
begin
Select OrderId, OrderName, OrderNumber, TotalAmount,Created,PaymentDate,o.Status, o.PaymentMethod from Orders o
where  o.UserId = @id  order by orderId desc
end
go

CREATE proc getOrderNew
as
begin
Select * from Orders where Status = '1'
end
go

create proc getTotalOrder
@total int output
as
begin
Select @total = COUNT(OrderId) from Orders where MONTH(Created) = MONTH(getDate())
end
go


CREATE proc getOrderDetailById
@id int
as
begin
Select OrderDetailId,OrderId,ProductName,o.Price, o.Quantity,o.ProductId,Amount, o.Status from OrderDetail o,Product p where o.ProductId = p.ProductId and o.OrderId = @id order by OrderId desc
end
go

CREATE proc updateOrder
@id int
as
begin
Update Orders set  PaymentDate = null, Status = 2
				where OrderId = @id
				end
go


CREATE proc insertOrder
@orderName nvarchar(100),
@orderNumber nvarchar(100),
@phone varchar(15),
@email nvarchar(100),
@address nvarchar(100),
@total float,
@idUser int
as
begin
Insert into Orders(OrderName,OrderNumber,Phone,Email,Address,TotalAmount,Created,PaymentDate,Status,UserId)
values(@orderName,@orderNumber,@phone,@email,@address,@total,GETDATE(),getDate(),1,@idUser)
end
go

CREATE proc insertOrderDetail
@id int,
@idProduct int,
@maount float,
@price float,
@quan int

as
begin
Insert into OrderDetail(OrderId,ProductId,Amount,Price,Quantity,Status)
values(@id,@idProduct,@maount,@price,@quan,1)
end
go

create proc getOrderIdNew
@id int output
as
begin
Select top 1 @id = OrderId from Orders order by OrderId desc
end
go

CREATE proc getTotalMoney
@total float output
as
begin
select @total = Sum(TotalAmount) from Orders where MONTH(Created) = MONTH(getDate())
end
go

Create proc updatePaymentMethod
@id int,
@name nvarchar(100)
as
begin
Update Orders set PaymentMethod = @name
                   where Orderid = @id
				   end
go

CREATE proc getOrderIdNewByUserId
@id int,
@out int output
as
begin
Select @out = OrderId from Orders where UserId = @id order by OrderId asc
end
go

create proc confirmOrder
@id int
as
begin
Update Orders set   Status = 3
				where OrderId = @id
				end
go


create proc getAllPaymentType
as
begin
select * from PaymentType where Status = 'True'
end
go


create proc insertPaymentType
@name nvarchar(100),
@des nvarchar(100)
as
begin
insert into PaymentType(PaymentTypeName,Descriptions,Status)
values (@name,@des,'True')
end
go

create proc updatePaymentType
@id int,
@name nvarchar(100),
@des nvarchar(100),
@status bit
as
begin
update PaymentType set PaymentTypeName = @name,
					   Descriptions = @des,
					   Status = @status
					where PaymentTypeId = @id

end
go

create proc deletePaymentType
@id int
as
begin
update PaymentType set  Status = 'False'
					where PaymentTypeId = @id

end
go

create proc checkPaymentTypeName
@name nvarchar(100),
@re bit output
as
begin
   if EXISTS(SELECT @name FROM PaymentType where PaymentTypeName = @name)
      set @re = 'True'
   else 
      set @re = 'False'
end
go

create proc checkExistsPaymentTypeName
@id int,
@name nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @name FROM PaymentType where PaymentTypeName = @name and PaymentTypeId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

create proc getPaymentTypeById
@id int
as
begin
Select * from PaymentType
end
go

create proc getAllProduct
as
begin

   select * from Product where Status = 'True'
end
go

create proc getProductById
@id int
as
begin
Select * from Product where ProductId = @id
end
go

create proc getProductReference
@id int,
@idCata int
as
begin
Select * from Product where CatalogId = @idCata and ProductId != @id
end
go

CREATE proc getProductByCatalog
@id int
as
begin
Select top 6  p.ProductId, p.ProductName, p.ProductContent, p.ProductContentDetail,p.CatalogId, p.Image, p.SupplierId, p.Quantity,p.Created, p.Status, p.Discount from Product p, Catalog c where p.CatalogId = c.CatalogId and c.ParentId = @id or p.CatalogId = c.CatalogId and p.CatalogId = @id
end
go

CREATE proc getProductByCatalogId
@id int
as
begin
Select  p.ProductId, p.ProductName, p.ProductContent, p.ProductContentDetail,p.CatalogId, p.Image, p.SupplierId, p.Quantity,p.Created, p.Status, p.Discount from Product p, Catalog c where p.CatalogId = c.CatalogId and c.ParentId = @id or p.CatalogId = @id and p.CatalogId = c.CatalogId
end
go

CREATE proc insertProduct
@name nvarchar(100),
@catalogid int,
@content ntext,
@contentDetail ntext,
@image nvarchar(100),
@price float,
@quantity int,
@supplier int,
@discount int
as
begin
insert into Product(ProductName,CatalogId,ProductContent,ProductContentDetail,Image, Price,Quantity,SupplierId,Created,Status,Discount)
values(@name,@catalogid,@content,@contentDetail,@image,@image,@price,@quantity,@supplier,GETDATE(),'True',@discount)
end
go

CREATE proc updateProduct
@id int,
@name nvarchar(100),
@catalogid int,
@content ntext,
@contentDetail ntext,
@image nvarchar(100),
@price float,
@quantity int,
@supplier int,
@discount int,
@status bit

as
begin
update Product set ProductName = @name,
				   CatalogId = @catalogid,
				   ProductContent = @content,
				   ProductContentDetail = @contentDetail,
				   Image = @image,
				   			
				   Quantity = @quantity,
				   SupplierId = @supplier,
				   Price = @price,
				  
				   Discount = @discount,
				   Status = @status
				   where ProductId = @id

end
go

create proc deleteProduct
@id int
as
begin
update Product set Status ='False' where ProductId = @id
end
go

create proc getProductNew
as
begin
select top 6 * from Product order by Created desc
end
go

create proc searchProduct
@stri nvarchar(100)
as
begin
Select * from Product where ProductName like N'%'+ @stri + N'%' 
end
go

create proc searchProductByProvider
@supplierId int
as
begin
Select * from Product where SupplierId = @supplierId
end
go

create proc getAllSupplier
as
begin
	Select * from Supplier where Status ='True'
end
go

create proc insertSupplier
@name nvarchar(100),
@add nvarchar(100),
@email nvarchar(100),
@phone varchar(15)
as
begin
Insert into Supplier(SupplierName,SupplierAddress,SupplierMail,SupplierPhone,SupplierStatus) values(@name,@add,@email,@phone,'True');
end
go

create proc checkSupplierName
@name nvarchar(100),
@re bit output
as
begin
   if EXISTS(SELECT @name FROM Supplier where SupplierName = @name)
      set @re = 'True'
   else 
      set @re = 'False'
end
go

create proc checkExistSupplierName
@id int,
@name nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @name FROM Supplier where SupplierName = @name and SupplierId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

create proc getSupplierById
@id int
as
begin
Select * from Supplier where SupplierId = @id
end
go

create proc updateSupplier
@id int,
@name nvarchar(100),
@add nvarchar(100),
@email nvarchar(100),
@phone varchar(15),
@status bit
as
begin
Update Supplier set  SupplierName = @name, 
                    SupplierAddress = @add, 
					SupplierMail = @email, 
					SupplierPhone = @phone, 
					SupplierStatus = @status where SupplierId = @id
end
go

create proc deleteProvider
@id int
as
begin
update Supplier set Status = 'False' where SupplierId = @id
end
go

create proc getAllUser
as
begin
Select * from Users where Status = 'True'
end
go

create proc insertUser
@name nvarchar(100),
@pass nvarchar(100),
@phone varchar(15),
@email nvarchar(100),
@address nvarchar(100),
@groupId int
as
begin
Insert into Users(UserName,Password,Phone,Mail,Address,GroupId,Status)
values (@name,@pass,@phone,@email,@address,@groupId,'True')
end
go

CREATE proc insertAccount
@name nvarchar(100),
@pass nvarchar(100),
@phone varchar(15),
@email nvarchar(100),
@address nvarchar(100)
as
begin
Insert into Users(UserName,Password,Phone,Mail,Address,GroupId,Status,Created)
values (@name,@pass,@phone,@email,@address,2,'True',getDate())
end
go

create proc activeAccount
@id int
as
begin
update Users set Status = 'True' where UserId = @id
end
go

create proc checkUserName
@name nvarchar(100),
@re bit output
as
begin
   if EXISTS(SELECT @name FROM Users where UserName = @name)
      set @re = 'True'
   else 
      set @re = 'False'
end
go

create proc checkExistUserName
@id int,
@name nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @name FROM Users where UserName = @name and UserId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

create proc checkUserEmail
@mail nvarchar(100),
@re bit output
as
begin
if exists(select @mail from Users where Mail = @mail)
	set @re = 'True'
else
	set @re = 'False'
end
go

create proc checkExistsUserEmail
@id int,
@mail nvarchar(100),
@re bit output
as
begin
if EXISTS(SELECT @mail FROM Users where Mail = @mail and UserId != @id)
    set @re = 'True'
else
   set @re ='False'
end
go

create proc getUserById
@id int
as
begin
Select * from Users where UserId = @id
end
go

create proc updateUser
@id int,
@name nvarchar(100),
@pass nvarchar(100),
@phone varchar(15),
@email nvarchar(100),
@address nvarchar(100),
@groupId int,
@status bit
as
begin
Update Users set UserName = @name,
			Password = @pass,
			Phone = @phone,
			Mail = @email,
			Address = @address,
			GroupId = @groupId,
			Status = @status 
			where UserId = @id
end
go

Create proc updateAccount
@id int,
@name nvarchar(100),
@pass nvarchar(100),
@phone varchar(15),
@email nvarchar(100),
@address nvarchar(100)
as
begin
Update Users set UserName = @name,
			Password = @pass,
			Phone = @phone,
			Mail = @email,
			Address = @address
			where UserId = @id
end
go
create proc deleteUser
@id int
as
begin
Update Users set Status = 'False' where UserId = @id
end
go

create proc getUserByName
@name nvarchar(100)
as
begin
Select * from Users where UserName = @name
end
go

create proc getTotalUser
@total int output
as
begin
Select @total = COUNT(UserId) from Users 
end
go

create proc getUserByEmail
@email nvarchar(100)
as
begin
Select * from Users where Mail = @email
end
go

create proc recoverPassword
@id int,
@pas nvarchar(100)
as
begin
Update Users set Password = @pas
             where UserId = @id
			 end
go

create proc getUserIdNew
@id int output
as
begin
Select @id = UserId from Users order by UserId desc
end
