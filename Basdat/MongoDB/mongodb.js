// Create
db.createCollection("customers");
db.createCollection("products");
db.createCollection("orders");

// Retrieving
db.getCollectionNames();

db.customers.find();
db.products.find();
db.orders.find();

db.products.find({
    $and: [
        {
            category: {
                $in: ["Laptop", "Smartphone"]
            }
        },
        {
            price: {
                $gte: 10000000
            }
        }
    ]
})

// Insert
db.customers.insertOne({
    _id: "cst001",
    name: "Gendhi Ramona",
})

db.products.insertMany([
    {
        _id: "pdt007",
        name: "Iphone 14",
        price: new NumberLong("11000000"),
        category: "Laptop",
    },
    {
        _id: "pdt008",
        name: "LG OLED HD",
        price: new NumberLong("40000000"),
        category: "Television",
    }
])

db.orders.insertOne({
    _id: new ObjectId(),
    total: new NumberLong("24000000"),
    items: [
        {
            product_id: "pdt001",
            price: new NumberLong("9000000"),
            quantity: new NumberInt("1")
        },
        {
            product_id: "pdt002",
            price: new NumberLong("15000000"),
            quantity: new NumberInt("1")
        }
    ]
})

// Update
db.products.updateOne({
    _id: "pdt007",
},{
    $set: {
        category: "Smartphone",
    }
})

db.products.updateMany({
    $and: [
        {
            _id: "pdt002",
        }
    ]
},{
    $set: {
        category: "Handheld",
    }
})

// Delete
db.customers.deleteOne({
    _id: "testDel"
})

