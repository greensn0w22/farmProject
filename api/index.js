const express = require("express");
const app = express();
const mysql = require("mysql");

var pool = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "445g6h7yge4f",
    database: "farmProject",
});

app.get("/", (req, res) => {
    pool.query("SELECT * from usager ", (err, rows) => {
        if (err) throw err;
        res.send(rows);
    });
});

app.post("/inscrireUnTonnage", (req, res) => {
    pool.query(
        "SELECT api_key FROM usager WHERE = $1", [req.params.user_id],
        (err, rows) => {
            if (rows[0].api_key === req.token) {
                pool.query(
                    "INSERT INTO tonnage (date_entry,number_of_pound,is_valid,usager_fk,field_fk) VALUES (now(),$1,$2,$3,$4)", [req.body.numberOfPound, req.isValid, req.usagerFk, req.fieldPf],
                    (err) => {
                        if (err) throw err;
                        res.send("Good");
                    }
                );
            }
        }
    );
});

app.listen(3000, () => {
    console.log("Server is running at port 3000");
});