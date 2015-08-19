DROP DATABASE IF EXISTS music_store;

CREATE DATABASE music_store;

USE music_store;

CREATE TABLE CUSTOMER (CUSTOMERID BIGINT NOT NULL, EMAIL VARCHAR(255),
FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), ADDRESS_ADDRESSID BIGINT,
CREDITCARD_CREDITCARDID BIGINT, PRIMARY KEY (CUSTOMERID));

CREATE TABLE CREDITCARD (CREDITCARDID BIGINT NOT NULL, CARDTYPE INTEGER,
EXPIRATIONDATE VARCHAR(255), NUMBER VARCHAR(255), PRIMARY KEY (CREDITCARDID));

CREATE TABLE PRODUCT (PRODUCTID BIGINT NOT NULL, ALBUMINFORMATION TEXT, CODE
VARCHAR(255), CSALBUMTITLES TEXT, DESCRIPTION VARCHAR(255), PRICE
DECIMAL(8,2), PRIMARY KEY (PRODUCTID));

CREATE TABLE INVOICE (NUMBER BIGINT NOT NULL, INVOICEDATE DATETIME, PROCESSED
TINYINT(1) default 0, CUSTOMER_CUSTOMERID BIGINT, PRIMARY KEY (NUMBER));

CREATE TABLE SUBSCRIBER (SUBSCRIBERID BIGINT NOT NULL, EMAIL VARCHAR(255),
FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), PRIMARY KEY (SUBSCRIBERID));

CREATE TABLE ADDRESS (ADDRESSID BIGINT NOT NULL, ADDRESS1 VARCHAR(255),
ADDRESS2 VARCHAR(255), CITY VARCHAR(255), COMPANYNAME VARCHAR(255), COUNTRY
VARCHAR(255), COUNTY VARCHAR(255), POSTCODE VARCHAR(255), PRIMARY KEY
(ADDRESSID));


CREATE TABLE LINEITEM (LINEITEMID BIGINT NOT NULL, QUANTITY INTEGER,
PRODUCT_PRODUCTID BIGINT, PRIMARY KEY (LINEITEMID));

CREATE TABLE DOWNLOAD (DOWNLOADID BIGINT NOT NULL, DOWNLOADDATE DATETIME,
CUSTOMER_CUSTOMERID BIGINT, PRODUCT_PRODUCTID BIGINT, PRIMARY KEY
(DOWNLOADID));

CREATE TABLE INVOICE_LINEITEM (Invoice_NUMBER BIGINT NOT NULL,
lineItems_LINEITEMID BIGINT NOT NULL, PRIMARY KEY (Invoice_NUMBER,
lineItems_LINEITEMID));

ALTER TABLE CUSTOMER ADD CONSTRAINT FK_CUSTOMER_CREDITCARD_CREDITCARDID
FOREIGN KEY (CREDITCARD_CREDITCARDID) REFERENCES CREDITCARD (CREDITCARDID);

ALTER TABLE CUSTOMER ADD CONSTRAINT FK_CUSTOMER_ADDRESS_ADDRESSID FOREIGN KEY
(ADDRESS_ADDRESSID) REFERENCES ADDRESS (ADDRESSID);

ALTER TABLE INVOICE ADD CONSTRAINT FK_INVOICE_CUSTOMER_CUSTOMERID FOREIGN KEY
(CUSTOMER_CUSTOMERID) REFERENCES CUSTOMER (CUSTOMERID);

ALTER TABLE LINEITEM ADD CONSTRAINT FK_LINEITEM_PRODUCT_PRODUCTID FOREIGN KEY
(PRODUCT_PRODUCTID) REFERENCES PRODUCT (PRODUCTID);

ALTER TABLE DOWNLOAD ADD CONSTRAINT FK_DOWNLOAD_CUSTOMER_CUSTOMERID FOREIGN
KEY (CUSTOMER_CUSTOMERID) REFERENCES CUSTOMER (CUSTOMERID);

ALTER TABLE DOWNLOAD ADD CONSTRAINT FK_DOWNLOAD_PRODUCT_PRODUCTID FOREIGN KEY
(PRODUCT_PRODUCTID) REFERENCES PRODUCT (PRODUCTID);

ALTER TABLE INVOICE_LINEITEM ADD CONSTRAINT FK_INVOICE_LINEITEM_Invoice_NUMBER
FOREIGN KEY (Invoice_NUMBER) REFERENCES INVOICE (NUMBER);

ALTER TABLE INVOICE_LINEITEM ADD CONSTRAINT
FK_INVOICE_LINEITEM_lineItems_LINEITEMID FOREIGN KEY (lineItems_LINEITEMID)
REFERENCES LINEITEM (LINEITEMID);

CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME));

SELECT * FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN';

INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);

UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 50 WHERE SEQ_NAME = 'SEQ_GEN';

SELECT SEQ_COUNT FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN';

INSERT INTO PRODUCT (PRODUCTID, ALBUMINFORMATION, CODE, CSALBUMTITLES,
DESCRIPTION, PRICE) VALUES (1, 'The debut album from 86 (the band), True Life
Songs and Pictures rocks and twangs in equal measure. Filled with banjo, one-
string bass, fiddle, and 3-part harmonies, this semi-rock, semi-country, semi-
bluegrass album covers a lot of ground. "How to Get There" is a rambling epic
that unleashes a rapid-fire lyrical barrage while "Don\'t Close Your Eyes" and
"Morning Sun" are more melancholy and bittersweet.', '8601', 'How to Get
There,You Are a Star,Wildflowers,Unidentified Fiddling Object,Beat Up Old
Car,Don\'t Make No Difference,What You Whistle When You Walk Home,Three Sheets
to the Wind,Singin\' Drunk,Don\'t Close Your Eyes,Morning Sun', '86 (the band)
- True Life Songs and Pictures',
32.99000000000000198951966012828052043914794921875);

INSERT INTO PRODUCT (PRODUCTID, ALBUMINFORMATION, CODE, CSALBUMTITLES,
DESCRIPTION, PRICE) VALUES (2, 'This 68-minute opus from San Francisco\'s
Paddlefoot doesn\'t pull any punches. Songs like "64 Corvair, Part 2",
"Whiskey Before Breakfast", and "The Murphy-Ryan Polkas" mix traditional
American and Irish fiddle tunes with indie rock. The result is somewhere
between The Pogues, Camper Van Beethoven, and Uncle Tupelo. The sincerity and
quirkiness of other songs like "When I Was Nine" and "Tiny House" are more
reminiscent of Jonathan Richman.', 'pf01', 'Pete and Jimmy,Whiskey Before
Breakfast,Fishing Rod,The Murphy-Ryan Polkas,John Henry\'s Blues,64 Corvair,
Part 2,Racoons Like Moonshine Too,Shelf Life,Hey Chris,Rock and Roll
Scene,Tiny House,Strum Along Slow,She\'s Moving Back Home,When I Was
Nine,Kangaroo\'s Paw,Sound of the Fiddle,Amatxi Smiled,Yeah,Dashboard Waltz',
'Paddlefoot - The first CD',
12.949999999999999289457264239899814128875732421875);

INSERT INTO PRODUCT (PRODUCTID, ALBUMINFORMATION, CODE, CSALBUMTITLES,
DESCRIPTION, PRICE) VALUES (3, 'The second CD from San Francisco\'s Paddlefoot
finds the band maturing as it roams through much of the same musical terrain
as the previous CD. While this album occasionally rocks, it also has its
introspective and bittersweet moments. "Neon Lights" is a romantic tribute to
neon lights, "Twist Away" is a heartfelt song of longing and loss, and "Let
the Possums Play Possum" is a instrumental romp that\'s driven by dual
fiddles. If you liked the first CD, you\'ll like this one too.', 'pf02', 'Just
About Midnight,Neon Lights,Take a Picture,Let the Possums Play Possum,Find
Me,Tank Hill,Billy Banjo,Most of the Time,Armenian Wedding,West Portal,Twist
Away,Kern County Waltz,Distance,Sunshine on the Trees', 'Paddlefoot - The
second CD', 11.949999999999999289457264239899814128875732421875);

INSERT INTO PRODUCT (PRODUCTID, ALBUMINFORMATION, CODE, CSALBUMTITLES,
DESCRIPTION, PRICE) VALUES (4, 'The debut album from Joe Rut rambles from
Byrds-esque folk pop of "Filter" to the country sounds of "Find My Way Marie"
to psychedelic Brit-pop tunes like "A Place In All This." This well-crafted
album is unique and cohesive, revealing its many layers on repeated listens.',
'jr01', 'Find My Way Marie,Filter,Hole,1400 Years,A Tuna Is a Damn Big Fish,So
Long Lazy Ray,El Dorado,Dream of You,This Sea Is Full of Monsters,A Place in
All This,GTTSWMD,AM Land,Whole Month of Sundays,Penny From a Poor Man', 'Joe
Rut - Genuine Wood Grained Finish', 14.5);

