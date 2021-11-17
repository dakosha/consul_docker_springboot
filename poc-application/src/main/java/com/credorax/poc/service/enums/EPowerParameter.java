package com.credorax.poc.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EPowerParameter {
    // Identification information fields.
    MERCHANT_ID("M", 3, 8, "Credorax assigned gateway merchant ID."),
    CIPHER("K", 1, 32, "Unique cipher used to authenticate requests."),
    OPERATION_CODE("O", 1, 3, "Operation code, used to determine requested service."),
    API_VERSION("V", 1, 5, "API version."),

    // Transaction details.
    REQUEST_ID("a1", 1, 32, "Request ID. Must be unique per merchant."),
    SOURCE_TYPE("a2", 1, 2, "Payment source type. May be one of several enumerated options."),
    BILLING_AMOUNT("a4", 1, 12, "Requested billing amount."),
    CURRENCY("a5", 3, 3, "Presentment currency.  Based on the ISO-4217-alpha-3 standard."),
    TRANSACTION_DATE("a6", 6, 6, "Transaction date."),
    TRANSACTION_TIME("a7", 6, 6, "Transaction time."),
    INVOICE_ID("a8", 1, 16, "Invoice ID.  Required for subscription transactions only."),
    RECURRING_TYPE("a9", 1, 2, "Indicates the recurring type of the transaction."),
    AUTH_TYPE("a10", 1, 1, "Indicates the authorization type of the transaction."),
    CAPTURE_COUNT("a11", 1, 2, "Expected capture count for the given authorization."),
    SKIP_CARD_VALIDATION("a12", 1, 1, "Allows token creation without payment."),
    FINAL_MULTI_CAPTURE("a13", 1, 1, "Indicates whether the transaction is the final capture."),
    PARTIAL_AUTHORIZATION("a14", 1, 1, "Partial authorization."),
    SUBTOTAL_AMOUNT("a41", 1, 12, "Subtotal amount."),
    VALUE_ADDED_TAX_AMOUNT("a42", 1, 12, "Value-added tax amount."),
    SHIPPING_AMOUNT("a44", 1, 12, "Shipping amount."),
    TIP_AMOUNT("a46", 1, 12, "Tip amount."),

    REQUESTED_PROCESSOR("r1", 1, 20, "Requested processor."),
    REQUESTED_PROCESSOR_MID("r2", 1, 40, "Requested processor mid."),
    CREDORAX_ROUTING_ORDER("r3", 1, 2, "Indicates the order of Credorax in merchant routing."),

    TOKEN_CRYPTO("token_crypto", 28, 40, "Online payment token cryptogram."),
    TOKEN_ECI("token_eci", 1, 2, "Eci indicator."),

    // Payment card account details.
    PRIMARY_ACCOUNT_NUMBER("b1", 8, 19, "Primary account number, or card number."),
    CARD_TYPE("b2", 1, 2, "Card type."),
    CARD_EXPIRATION_MONTH("b3", 2, 2, "Card expiration month."),
    CARD_EXPIRATION_YEAR("b4", 2, 2, "Card expiration year."),
    CARD_CVV("b5", 3, 4, "Card security code."),
    CARD_SEQUENCE_NUMBER("b6", 0, 3, "Card sequence number."),
    TRACK_2_DATA("b7", 10, 37, "Track 2 data."),
    TRACK_1_DATA("b8", 76, 76, "Track 1 data."),
    CARD_ENTRY_MODE("b9", 2, 2, "Card entry mode."),
    PIN_ENTRY_CAPABILITY("b10", 1, 1, "PIN entry capability."),
    TRANSACTION_SEQUENCE_COUNTER("b11", 1, 8, "Transaction sequence counter, maintained by the terminal."),
    UNIQUE_TERMINAL_ID("b12", 1, 256, "Unique terminal ID (IMEI/hardware based)."),
    CARDHOLDER_SIGNATURE_IMAGE("b13", 1, 60000, "Cardholder signature image."),
    GPS_LATITUDE("b14", 15, 15, "GPS latitude."),
    GPS_LONGITUDE("b15", 15, 15, "GPS longitude."),
    CARDHOLDER_PHOTO("b16", 1, 60000, "Cardholder photo."),
    CARD_ACCEPTOR_TERMINAL_ID("b17", 1, 8, "Card acceptor terminal ID."),
    OFFLINE_PIN_INDICATOR("b18", 1, 1, "Offline Pin Indicator (where the pin is approved offline)."),
    UCAT_TERMINAL_INDICATOR("b19", 2, 2, "Ucat Terminal Indicator."),
    MOBILE_PAYMENT_SCHEME("b21", 8, 10, "Mobile payment scheme."),
    ENCRYPTED_PIN_BLOCK("b52", 16, 16, "Encrypted PIN block."),
    ZMK_KEY_INDEX("b53", 4, 6, "Zone master key index."),
    ICC_DATA("b55", 150, 999, "ICC data."),

    // Cardholder details.
    CARDHOLDER_FULL_NAME("c1", 5, 64, "Cardholder full name."),
    CARDHOLDER_PHONE_NUMBER("c2", 5, 32, "Cardholder phone number."),
    CARDHOLDER_EMAIL("c3", 7, 64, "Cardholder email address."),
    CARDHOLDER_STREET_NUMBER("c4", 1, 16, "Cardholder street number."),
    CARDHOLDER_STREET_NAME("c5", 4, 64, "Cardholder street name."),
    CARDHOLDER_CITY("c7", 3, 32, "Cardholder city."),
    CARDHOLDER_TERRITORY("c8", 1, 3, "Cardholder territory/state/province, based on ISO-3166-2."),
    CARDHOLDER_COUNTRY_CODE("c9", 2, 2, "Cardholder country code, based on ISO-3166-1-alpha-2."),
    CARDHOLDER_POSTCODE("c10", 4, 15, "Cardholder post/zip code."),
    CARDHOLDER_FIRST_NAME("c22", 1, 50, "Cardholder first name."),
    CARDHOLDER_LAST_NAME("c23", 1, 50, "Cardholder last name."),

    // Additional Fields
    CUSTOMER_LEVEL("c12", 0, 32, "Customer level VIP/Basic."),
    CUSTOMER_USER_ID("c13", 0, 32, "Customer user ID - shopper/player ID."),
    CUSTOMER_CREATION_DATE("c14", 8, 8, "Customer creation date."),
    CUSTOMER_DATE_OF_BIRTH("c15", 8, 8, "Customer date of birth."),
    CUSTOMER_USERNAME("c16", 0, 32, "Customer Date of Birth."),
    CUSTOMER_EMAIL("c17", 3, 255, "Customer email."),
    CUSTOMER_ID("c18", 0, 32, "Customer ID/Passport number."),
    USER_DEFINED_FIELD_1("c19", 0, 255, "User defined field #1."),
    USER_DEFINED_FIELD_2("c20", 0, 255, "User defined field #2."),
    USER_DEFINED_FIELD_3("c21", 0, 255, "User defined field #3."),

    // Fraud related fields
    DISABLE_FRAUD_SERVICES("f21", 1, 1, "Activate or deactivate fraud services"),
    OVERRIDE_FRAUD_MAX_THRESHOLD("f22", 1, 4, "Fraud max scoring threshold override by merchant"),
    THREEDS_FRAUD_THRESHOLD("f23", 1, 4, "Fraud max scoring threshold for smard3D risk submodule"),

    // General details and tuning fields.
    CARDHOLDER_IP("d1", 7, 15, "Cardholder IP address."),
    ECHO("d2", 1, 128, "Echo parameter."),
    USER_AGENT("d5", 5, 255, "Customer's browser related User-Agent HTTP header. Indicates browser type."),
    ACCEPT_LANGUAGE("d6", 2, 16, "Customer's browser related Accept-Language HTTP header."),
    BROWSER_VERSION("d8", 0, 64, "Browser version."),
    DEVICE_TYPE("d9", 0, 64, "Device type mobile/tablet/iPad/desktop."),
    DEVICE_OS_NAME("d10", 0, 64, "Device operating system name."),
    DEVICE_OS_VERSION("d11", 0, 64, "Device operating system version."),

    ON_BEHALF_MERCHANT_ID("h2", 3, 8, "Credorax assigned gateway merchant ID."),
    SUB_MERCHANT_IDENTIFIER("h3", 1, 16, "Sub-merchant identifier."),
    TRX_SOURCE("h4", 1, 32, "Transaction's source"),
    DEVICE_ADDRESS("h5", 0, 108, "SM / device address for dynamic setup"),
    SUBSCRIPTION_DETAILS("h6", 6, 2048, "Subscription details."),
    SUBSCRIPTION_BILLING_CYCLE("h7", 1, 3, "Current subscription billing cycle."),
    SUB_MERCHANT_PHONE_NUMBER("h8", 5, 32, "Sub merchant phone number"),
    MERCHANT_REFERENCE_NUMBER("h9", 1, 15, "Merchant reference number."),
    CURRENT_SUBSCRIPTION_BILLING_ENTRY("h10", 1, 1, "Current subscription billing entry."),
    SUB_MERCHANT_INFORMATION("h11", 12, 900, "Sub-merchant information."),
    SUB_MERCHANT_URLS("h12", 4, 500, "Sub-merchant urls."),
    CP_DEVICE_CODE("h13", 1, 3, "Holds the information of the device type that is being setup."),
    MARKETPLACE_INFORMATION("h15", 9, 256, "Marketplace information to support Foreign retailer indication logic."),
    STORE_ID("h20", 1, 32, "Store id for APM stores"),
    STORE_NAME("h21", 1, 32, "Store name for APM stores"),

    TRANSACTION_DESCRIPTION("i1", 5, 256, "Free text description of the transaction."),
    BILLING_DESCRIPTOR("i2", 1, 39, "Billing descriptor.  Appears on the cardholder statement."),
    TRANSFER_SENDER_FULL_NAME("i3", 5, 25, "Transfer sender full name."),
    TRANSFER_SENDER_REFERENCE_NUMBER("i4", 5, 19, "Transfer sender reference number."),
    TRANSFER_SENDER_STREET_ADDRESS("i5", 4, 64, "Transfer sender street address."),
    THREED_SECURE_DATA("i8", 10, 128, "3D Secure Data."),
    TRANSFER_SENDER_CITY("i9", 3, 32, "Transfer sender city."),
    TRANSFER_SENDER_COUNTRY("i10", 2, 2, "Transfer sender country."),
    TRANSFER_SENDER_STATE("i11", 1, 3, "Transfer sender territory/state/province."),
    TRANSFER_SENDER_POSTCODE("i12", 4, 15, "Transfer sender post/zip code."),
    @Deprecated
    CFT_TYPE("i13", 1, 2, "CFT type."),
    TRANSFER_SENDER_ACCOUNT_NUMBER("i15", 1, 16, "Transfer sender account number (PAN)."),

    RECIPIENT_DATE_OF_BIRTH("j1", 8, 8, "Date of birth of primary account recipient."),
    RECIPIENT_ACCOUNT_NUMBER("j2", 1, 10, "Recipient masked PAN or account number from merchant system."),
    RECIPIENT_POSTCODE("j3", 2, 6, "Recipient postcode."),
    RECIPIENT_PARTIAL_SURNAME("j4", 2, 6, "Recipient partial surname."),
    RECIPIENT_ACCOUNT_FIRST_NAME("j5", 1, 30, "Recipient account first name."),
    RECIPIENT_STREET_ADDRESS("j6", 1, 30, "Recipient street address."),
    RECIPIENT_CITY("j7", 1, 25, "Recipient city."),
    RECIPIENT_STATE_OR_PROVINCE_CODE("j8", 3, 3, "Recipient state or province code."),
    RECIPIENT_COUNTRY_CODE("j9", 3, 3, "Recipient country code."),
    RECIPIENT_PHONE_NUMBER("j10", 1, 20, "Recipient phone number."),
    RECIPIENT_ACCOUNT_DATE_OF_BIRTH("j11", 8, 8, "Recipient account date of birth."),
    RECIPIENT_ACCOUNT_POSTCODE("j12", 1, 10, "Recipient account postcode."),
    RECIPIENT_ACCOUNT_SURNAME("j13", 1, 30, "Recipient account surname."),

    // Referral details fields.
    TOKEN("g1", 1, 32, "Token generated by Credorax which references a stored card profile."),
    REFERRAL_RESPONSE_ID("g2", 1, 32, "The [z1] parameter from the corresponding past transaction."),
    REFERRAL_AUTHORIZATION_CODE("g3", 1, 10, "The [z4] parameter from the corresponding past transaction."),
    REFERRAL_REQUEST_ID("g4", 1, 32, "The [a1] parameter from the corresponding past transaction."),
    COMPLETION_REQUEST_ID("g5", 1, 32, "The [z1] parameter from the corresponding original transaction."),
    INITIAL_TRANSACTION_ID("g6", 16, 32, "Refelcts the previous linked transaction in the business chain."),

    // Retail/goods fields
    NUMBER_OF_ITEMS("re1", 0, 10, "Number of items."),
    INVOICE_NUMBER("re2", 0, 64, "Invoice number."),
    SHIPPING_CLASS("re3", 0, 64, "Shipping class regular/VIP/express."),
    EXPECTED_DELIVERY_DATE("re4", 8, 8, "Expected delivery date."),
    DISCOUNT_CODE("re5", 0, 64, "Discount code."),

    // Gaming and gambling fields
    ACCOUNT_BALANCE("ga1", 0, 16, "Account balance."),
    GAME_ID("ga2", 0, 64, "Game ID."),
    GAME_NAME("ga3", 0, 64, "Game name."),
    TIME_IN_GAME("ga4", 0, 64, "Time in game."),

    // Forex fields
    TRADED_CURRENCIES("fo1", 0, 9, "Traded currencies."),

    // OTA fields
    TICKET_NUMBER("ota1", 0, 64, "Ticket number."),
    TRAVEL_AGENCY_CODE("ota2", 0, 64, "Travel company."),
    PASSENGER_NAME_RECORD("ota3", 0, 64, "Passenger name record PNR."),
    CLASS_OF_TRAVEL("ota4", 0, 64, "Class of travle bussiness/economy."),
    TRAVEL_AGENCY("ota5", 0, 64, "Travel agency."),
    E_TICKET_EMIAL_ADDRESS("ota6", 3, 128, "E-ticket email address."),
    AIRLINE("ota7", 0, 64, "Airline."),
    TRAVEL_ORIGIN("ota8", 0, 2, "Travel origin."),
    TRAVEL_DESTINATION("ota9", 0, 2, "Travel destination."),
    FLIGHT_DATE("ota10", 8, 8, "Flight date."),
    NUMBER_OF_TRAVEL_STOPS("ota11", 0, 5, "Number of travel stops."),
    FREQUENT_FLYER_NUMBER("ota12", 0, 64, "Frequent flyer number."),
    RESTRICTED_TICKET_INDICATOR("ota14", 1, 1, "Restricted ticket indicator."),
    COMPUTER_RESERVATION_SYSTEM("ota15", 4, 4, "Computer reservation system."),
    CREDIT_REASON_INDICATOR("ota16", 1, 1, "Credit reason indicator."),
    TICKET_CHANGE_INDICATOR("ota17", 1, 1, "Ticket change indicator."),
    ISSUED_IN_CONNECTION_WITH_TICKET_NUMBER("ota18", 13, 13, "Issued in Connection With Ticket Number."),
    ANCILLARY_TICKET_DOCUMENT_NUMBER("ota19", 15, 15, "Ancillary Ticket Document Number."),
    AIRLINE_CODE("ota20", 1, 6, "The code of Airline."),
    CONTRACTOR_NAME("ota21", 1, 50, "The name of the contractor."),
    ATOL_CERTIFICATE_NUMBER("ota22", 1, 255, "ATOL certificate number."),
    NUMBER_OF_ISSUED_PASSENGERS("ota23", 1, 3, "Number of passengers for whom the ticket was issued."),
    AIRLINE_IATA_CODE("ota24", 1, 8, "IATA numeric code for the airline."),
    AIRLINE_CARRIER("ota25", 1, 25, "Airline carrier that generated the ticket."),
    AIRLINE_DOCUMENT_TYPE("ota26", 2, 2,
            "Airline document type code that specifies the purpose of the transaction."),
    ELECTRONIC_TICKET_ISSUED_INDICATOR("ota27", 1, 1,
            "Flag that indicates whether an electronic ticket was issued."),
    REPLACEMENT_ORIGINAL_TICKET_NUMBER("ota28", 1, 14,
            "Original ticket number when the transaction is for a replacement ticket."),
    AIRLINE_PROCESS_IDENTIFIER("ota29", 1, 3, "Airline process identifier."),
    AIRLINE_TRAVEL_PURCHASE_TYPE("ota30", 1, 3, "Type of purchase."),
    AIRLINE_TRAVEL_TRANSACTION_CITY("ota31", 1, 18, "City in which the transaction occurred."),
    AMEX_TICKET_NUMBER("ota32", 1, 14, "Ticket number or a value that American Express Direct."),
    BOOKING_REFERENCE_NUMBER("ota33", 1, 14, "Booking reference number field for capture or credit request."),
    AIRLINE_INVOICE_DATE("ota34", 1, 14, "Invoice date."),

    MOVIE_NAME("st1", 0, 64, "Movie or series name."),
    MOVIE_ID("st2", 0, 64, "Movie or series ID."),

    //Travel information

    FLIGHT_INFORMATION_1("fl1", 7, 38, "Flight information 1."),
    FLIGHT_INFORMATION_2("fl2", 7, 38, "Flight information 2."),
    FLIGHT_INFORMATION_3("fl3", 7, 38, "Flight information 3."),
    FLIGHT_INFORMATION_4("fl4", 7, 38, "Flight information 4."),
    FLIGHT_INFORMATION_5("fl5", 10, 10, "Flight departure date."),
    FLIGHT_INFORMATION_6("fl6", 10, 10, "Flight arrival date."),

    ANCILLARY_INFORMATION_1("an1", 1, 17, "Ancillary  information 1."),
    ANCILLARY_INFORMATION_2("an2", 1, 17, "Ancillary information 2."),
    ANCILLARY_INFORMATION_3("an3", 1, 17, "Ancillary information 3."),
    ANCILLARY_INFORMATION_4("an4", 1, 17, "Ancillary information 4."),

    EVENT_START_DATE("ev1", 10, 10, "Event start date."),
    EVENT_END_DATE("ev2", 10, 10, "Event end date."),
    EVENT_ORGANIZER_ID("ev3", 1, 50, "Event organizer ID."),
    EVENT_ID("ev4", 1, 50, "Event ID."),

    FURNITURE_SUPPLIER("fu1", 1, 50, "Name of the furniture supplier."),

    SUPPLIER_NAME("cr1", 1, 50, "The name of supplier/contractor."),

    // Card present params
    TERMINAL_PIN_ENTRY("s2", 1, 1, "Terminal Supports PIN entry."),
    RESPONSE_TO_PIN_REQUEST("s3", 1, 1, "Response to PIN Request."),
    SINGLE_TAP_MODE("s4", 1, 1, "Issuer requests a PIN in a Single Tap mode."),

    // 3ds fields

    THREEDS_STATUS("3ds_status", 1, 1, "The status of the authentication request as received from the acs"),
    THREEDS_INTERNAL_STATUS("3ds_internal_status", 6, 18, "The status of the threeds request as defined by x3d"),
    THREEDS_VALID_PAYMENT("3ds_valid_payment", 1, 1,
            "Indication if the transaction can proceed to payment following the 3D result"),
    THREEDS_INITIATE("3ds_initiate", 2, 2, "Indicates whether or not to allow payment with 3D"),
    THREEDS_TRX_ID("3ds_trxid", 36, 36, "The 3DS flow transaction ID."),
    THREEDS_CHANNEL("3ds_channel", 2, 2,
            "Indicates which type of platform is initiated the 3D secure call (browser, sdk, 3RI)."),
    THREEDS_CATEGORY("3ds_category", 2, 2, "Indicates the type of 3RI request."),
    THREEDS_COMP_IND("3ds_compind", 1, 1, "Indication if contains information for the 3DS Requestor."),
    THREEDS_SDK_INTERFACE("3ds_sdkinterface", 2, 2,
            "Specifies the SDK Interface type that the device supports."),
    THREEDS_SDK_UI_TYPE("3ds_sdkuitype", 2, 14, "Specifies the UI type that the device supports."),
    THREEDS_REQ_AUTH_METHOD("3ds_reqauthmethod", 2, 2,

            "Information about how the 3DS Requestor authenticated the cardholder before or during the transaction."),
    THREEDS_REQ_AUTH_TIMESTAMP("3ds_reqauthtimestamp", 12, 12,
            "Date and time in UTC of the cardholder authentication."),
    THREEDS_REQ_AUTH_DATA("3ds_reqauthdata", 0, 255,
            "Data that documents and supports a specific authentication process."),
    THREEDS_REQ_CHALLENGE_IND("3ds_reqchallengeind", 2, 2,
            "Indicates whether a challenge is requested for this transaction."),
    THREEDS_REQ_PRIOR_REF("3ds_reqpriorref", 36, 36,

            "Contains information about how the 3DS Requestor authenticated the cardholder as part of a previous 3DS transaction."),
    THREEDS_REQ_PRIOR_AUTH_METHOD("3ds_reqpriorauthmethod", 2, 2,
            "Mechanism used by the Cardholder to previously authenticate to the 3DS Requestor."),
    THREEDS_REQ_PRIOR_AUTH_TIMESTAMP("3ds_reqpriorauthtimestamp", 12, 12,
            "Date and time in UTC of the prior authentication."),
    THREEDS_REQ_PRIOR_AUTH_DATA("3ds_reqpriorauthdata", 0, 255,
            "Data that documents and supports a specfic authentication process."),
    THREEDS_CH_ACC_DATE("3ds_chaccdate", 8, 8,
            "Date that the cardholder opened the account with the 3DS Requestor."),
    THREEDS_CH_ACC_CHANGE_IND("3ds_chaccchangeind", 2, 2,
            "Interval since the cardholder’s account information with the 3DS Requestor was last changed."),
    THREEDS_CH_ACC_CHANGE("3ds_chaccchange", 8, 8,
            "Date that the cardholder’s account with the 3DS Requestor was last changed."),
    THREEDS_CH_ACC_PW_CHANGE_IND("3ds_chaccpwchangeind", 2, 2,
            "Interval the cardholder’s account with the 3DS Requestor had a password change or account reset."),
    THREEDS_CH_ACC_PW_CHANGE("3ds_chaccpwchange", 8, 8,
            "Date that cardholder’s account with the 3DS Requestor had a password change or account reset."),
    THREEDS_CH_ACC_MOBILE_PHONE("3ds_chmobilephone", 0, 18, "The mobile phone provided by the Cardholder"),
    THREEDS_CH_ACC_HOME_PHONE_COUNTRY("3ds_homephonecountry", 1, 3, "The card holder country home phone"),
    THREEDS_CH_ACC_MOBILE_PHONE_COUNTRY("3ds_mobilephonecountry", 1, 3, "The card holder country mobile phone"),
    THREEDS_CH_ACC_WORK_PHONE_COUNTRY("3ds_workphonecountry", 1, 3, "The card holder country work phone"),
    THREEDS_CH_ACC_WORK_PHONE("3ds_chworkphone", 0, 18, "The work phone provided by the Cardholder"),
    THREEDS_SHIP_ADD_USAGE_IND("3ds_shipaddressusageind", 2, 2,
            "Indicates when the shipping address used for this transaction was first used with the 3DS Requestor."),
    THREEDS_SHIP_ADD_USAGE("3ds_shipaddressusage", 8, 8,
            "Date when the shipping address used for this transaction was first used with the 3DS Requestor."),
    THREEDS_TXN_ACTIVITY_DAY("3ds_txnactivityday", 0, 10,
            "Number of transactions for this cardholder account with the 3DS Requestor in the previous 24 hours."),
    THREEDS_TXN_ACTIVITY_YEAR("3ds_txnactivityyear", 0, 10,
            "Number of transactions for this cardholder account with the 3DS Requestor in the previous year."),
    THREEDS_PROVISION_ATTEMPTS_DAY("3ds_provisionattemptsday", 0, 10,
            "Number of Add Card attempts in the last 24 hours."),
    THREEDS_NB_PURCHASE_ACCOUNT("3ds_nbpurchaseaccount", 0, 10,
            "Number of purchases with this cardholder account during the previous six months."),
    THREEDS_SUSPICIOUS_ACC_ACTIVITY("3ds_suspiciousaccactivity", 2, 2,
            "Indicates whether the 3DS Requestor has experienced suspicious activity."),
    THREEDS_SHIP_NAME_IND("3ds_shipnameindicator", 2, 2,
            "Indicates if the Cardholder Name on the account is identical to the shipping Name used for this transaction."),
    THREEDS_PAYMENT_ACC_IND("3ds_paymentaccind", 2, 2,
            "Interval of time that the payment account was enrolled in the cardholder’s account with the 3DS Requester."),
    THREEDS_PAYMENT_ACC_AGE("3ds_paymentaccage", 8, 8,
            "Date that the payment account was enrolled in the cardholder’s account with the 3DS Requestor."),
    THREEDS_ACC_ID("3ds_accid", 0, 64,
            "Additional information about the account optionally provided by the 3DS Requestor."),
    THREEDS_PAY_TOKEN_IND("3ds_paytokenind", 4, 5,
            "Indicates that the transaction was de-tokenized prior to being received by the ACS."),
    THREEDS_ADDRESS_MATCH("3ds_addrmatch", 4, 5,
            "Indicates whether the Cardholder Shipping Address and Cardholder Billing Address are the same."),
    THREEDS_SHIP_ADD_CITY("3ds_shipaddrcity", 3, 32,
            "City portion of the shipping address requested by the Cardholder."),
    THREEDS_SHIP_ADD_COUNTRY("3ds_shipaddrcountry", 2, 2,
            "Country of the shipping address requested by the Cardholder."),
    THREEDS_SHIP_ADD_LINE1("3ds_shipaddrline1", 0, 50,
            "First line of the street address associated with the card use for this purchase."),
    THREEDS_SHIP_ADD_LINE2("3ds_shipaddrline2", 0, 50,
            "Second line of the street address associated with the card use for this purchase."),
    THREEDS_SHIP_ADD_POSTCODE("3ds_shipaddrpostcode", 0, 16,
            "ZIP or other postal code of the shipping address associated with the card used for this purchase."),
    THREEDS_SHIP_ADD_STATE("3ds_shipaddrstate", 1, 3,
            "The state or province of the shipping address associated with the card used for this purchase."),
    THREEDS_SHIP_IND("3ds_shipindicator", 2, 2, "Indicates shipping method chosen for the transaction."),
    THREEDS_DELIVERY_TIMEFRAME("3ds_deliverytimeframe", 2, 2, "Indicates the merchandise delivery timeframe."),
    THREEDS_DELIVERY_EMAIL("3ds_deliveryemailaddress", 7, 64,
            "For electronic delivery, the email address to which the merchandise was delivered."),
    THREEDS_RE_ORDER_ITEMS_IND("3ds_reorderitemsind", 2, 2,
            "Indicates whether the cardholder is reoreding previously purchased merchandise."),
    THREEDS_PRE_ORDER_PURCHASE_IND("3ds_preorderpurchseind", 2, 2,
            "Indicates whether Cardholder is placing an order for merchandise with a future availability or release date."),
    THREEDS_PRE_ORDER_DATE("3ds_preorderdate", 8, 8,
            "For a pre-ordered purchase, the expected date that the merchandise will be available."),
    THREEDS_GIFT_CARD_AMOUNT("3ds_giftcardamount", 1, 12,
            "The purchase amount total of prepaid or gift card(s)."),
    THREEDS_GIFT_CARD_CURRENCY("3ds_giftcardcurr", 3, 3,
            "The currency code of the card as defined in ISO 4217."),
    THREEDS_GIFT_CARD_COUNT("3ds_giftcardcount", 0, 2,
            "Total count of individual prepaid or gift cards/codes purchased."),
    THREEDS_PURCHASE_DATE("3ds_purchasedate", 14, 14, "Date and time of ther purchase, expressed in UTC."),
    THREEDS_RECURRING_EXPIRY("3ds_recurringexpiry", 8, 8,
            "Date after which no further authorizations shall be performed."),
    THREEDS_RECURRING_FREQUENCY("3ds_recurringfrequency", 0, 4,
            "Indicates the minimum number of days between authorizations."),
    THREEDS_TRANS_TYPE("3ds_transtype", 2, 2, "Identifies the type of transaction being authenticated."),
    THREEDS_BROWSER_JAVA_ENABLED("3ds_browserjavaenabled", 4, 5,
            "Boolean that represents the ability of the cardholder browser to execute Java."),
    THREEDS_BROWSER_JAVASCRIPT_ENABLED("3ds_browserjavascriptenabled", 4, 5,
            "Boolean that represents the ability of the cardholder browser to execute JavaScript."),
    THREEDS_BROWSER_COLOR_DEPTH("3ds_browsercolordepth", 1, 2,
            "Value representing the bit depth of the colour palette for displaying images, in bits per pixel."),
    THREEDS_BROWSER_SCREEN_HEIGHT("3ds_browserscreenheight", 1, 6,
            "Total height of the Cardholder's screen in pixels."),
    THREEDS_BROWSER_SCREEN_WIDTH("3ds_browserscreenwidth", 1, 6,
            "Total width of the Cardholder's screen in pixels."),
    THREEDS_BROWSER_TZ("3ds_browsertz", 1, 5,
            "Time difference between UTC time and the Cardholder browser local time, in minutes."),
    THREEDS_BROWSER_ACCEPT_HEADER("3ds_browseracceptheader", 0, 2048,
            "Exact content of the HTTP accept headers as sent to the 3DS Requestor from the Cardholder's browser. This field is required for requests where deviceChannel=02 (BRW)."),
    THREEDS_CHALLENGE_WINDOW_SIZE("3ds_challengewindowsize", 2, 2,
            "Dimensions of the challenge window that has been displayed to the Cardholder."),
    THREEDS_SDK_APP_ID("3ds_sdkappid", 0, 36,
            "UUID created upon all installations and updates of the 3DS Requestor APp on a Customer Device."),
    THREEDS_SDK_ENC_DATA("3ds_sdkencdata", 0, 64,
            "JWE Object as defined Section 6.2.2.1 containing data encrypted by the SDK for the DS to decrypt."),
    THREEDS_SDK_EPHEM_PUB_KEY("3ds_sdkephempubkey", 0, 256, "Public key component of the ephemeral key pair."),
    THREEDS_SDK_MAX_TIMEOUT("3ds_sdkmaxtimeout", 2, 2,
            "Indicates the maximum amount of time (in min) for all exchanges."),
    THREEDS_SDK_REF_NUM("3ds_sdkreferencenumber", 0, 32, "Identifies the vendor and version of the 3DS SDK."),
    THREEDS_SDK_TRANS_ID("3ds_sdktransid", 0, 36,
            "UUID assigned by the 3DS SDK to identify a single transaction."),
    THREEDS_MSG_EXT_ID("3ds_msgextensionid", 0, 64,
            "A unique identifier for the extension. Payment System Registered Application Provider Identifier (RID)."),
    THREEDS_MSG_EXT_NAME("3ds_msgextensinoname", 0, 64,
            " The name of the extension data set as defined by the extension owner."),
    THREEDS_MSG_EXT_CRITICAL_IND("3ds_msgextcriticalityind", 4, 5,
            "A boolean value indicating whether the recipient must understand the contents of the extension to interpret the entire message."),
    THREEDS_MSG_EXT_DATA("3ds_msgextensiondata", 0, 8059, "The data carried in the extension"),
    THREEDS_MERCHANT_REDIRECT_URL("3ds_redirect_url", 0, 2048, "Merchant website to redirect back challenge"),
    //Merchant credentials
    THREEDS_MERCHANT_NAME("3ds_merchant_name", 2, 64, "Credentials merchat name"),
    THREEDS_ACQUIRER_BIN("3ds_acquirer_bin", 6, 6, "Credentials acquire bin"),
    THREEDS_ACQUIRER_PASSWORD("3ds_acquirer_password", 8, 32, "Credentials acquire password"),
    THREEDS_ACQUIRER_MID("3ds_acquirer_mid", 4, 32, "Credentials aquire gwMid"),
    THREEDS_MERCHANT_URL("3ds_merchant_url", 4, 256, "Credentials merchant url"),
    THREEDS_MERCHANT_COUNTRY("3ds_merchant_country", 3, 3, "Credentials merchant country"),
    THREEDS_MERCHANT_MCC("3ds_merchant_mcc", 4, 4, "Credentials merchants mcc"),
    THREEDS_REQUSTOR_ID("3ds_requestorid", 1, 35, "Credentials requestor id"),
    THREEDS_REQUSTOR_NAME("3ds_requestorname", 1, 35, "Credentials requestor name"),
    THREEDS_REQUSTOR_DEC_REQ_IND("3ds_reqdecreqind", 4, 5,
            "Indicates whether the 3DS Requestor requests the ACS to utilise Decoupled Authentication and agrees to utilise Decoupled Authentication if the ACS confirms its use"),
    THREEDS_REQUESTOR_DEC_MAX_TIME("3ds_reqdecmaxtime", 1, 5,
            " the maximum amount of time (in minutes) that the 3DS Requestor will wait for an ACS to provide the results of a Decoupled Authentication transaction"),
    THREEDS_WHITELIST_STATUS("3ds_whiteliststatus", 4, 5, "Set whitelisting status of the merchant"),
    // Smart 3D
    THREEDS_RBA_AMOUNT("3ds_rba_amount", 1, 10, "Smart 3D RBA threshold."),
    THREEDS_RBA_COUNTRIES("3ds_rba_countries", 0, 2048, "Smart 3D RBA countries."),
    EXEMPTION_ACTION("exemption_action", 2, 2,
            "The merchant desicsion if to go to 3DS or payment after examption."),
    EXEMPTION_REASON("exemption_reason", 2, 2, "The merchant reason for examption."),
    THREEDS_NOTIFICATION_ADDRESS("3ds_notification_address", 0, 2048, "The merchant notification address"),

    // X3d response
    THREEDS_VERSION("3ds_version", 1, 20, "The earliest active protocol version that is supported by the ACS."),
    THREEDS_METHOD("3ds_method", 1, 2048,
            "The ACS URL that will be used by the 3DS Method, retrieved from the card range data repository."),
    THREEDS_CHALLENGE("3ds_challenge", 1, 1,
            "Indicates whether a transaction qualifies as an authenticated transaction."),
    THREEDS_CAVV("3ds_cavv", 40, 40,
            "Payment System-specific value provided as part of the ACS registration for each supported DS. Authentication Value may be used to provide proof of authentication."),
    THREEDS_ECI("3ds_eci", 2, 2,
            "Payment System-specific value provided by the ACS to indicate the results of the attempt to authenticate the Cardholder."),
    THREEDS_XID("3ds_xid", 0, 40, "Hex encoded value requred for i8"),
    THREEDS_ACS_URL("3ds_acsurl", 1, 20,
            "Payment System-specific value provided as part of the ACS registration for each supported DS. "),
    THREEDS_DS_TRX_ID("3ds_dstrxid", 1, 36, "The received Authentication Response from the Directory Server."),
    THREEDS_PAREQ("3ds_pareq", 64, 64,

            "Payer Authentication Request Message passed to the Issuing Bank as part of the process of enabling the card holder to identify himself"),
    THREEDS_WHITELIST_STATUS_SOURCE("3ds_whiteliststatussource", 2, 2,
            "populated by the system setting Whitelist Status."),
    SMART3DS_RESULT("smart_3ds_result", 2, 2, "Smart 3DS result code."),
    SMART3DS_RESULT_REASON("smart_3ds_result_reason", 0, 128, "Smart 3DS result reason array."),
    // x3d app response
    THREEDS_ACS_TRANS_ID("3ds_acstrxid", 0, 36, "The acs trans Id."),
    THREEDS_ACS_REF_NUM("3ds_acsrefnumber", 0, 36, "The acs reference number"),
    THREEDS_ACS_SIGEND_CONTENT("3ds_acssignedcontent", 0, 36, "The acs signed content"),
    //Feedzai 301
    FEEDZAI_OPERATION_RESULT_SUCCESSFUL("operation_result_successful", 8, 18,
            "The response on the transaction."),
    FEEDZAI_OPERATION("operation", 4, 14, "The operation that the merchant has executed."),
    FEEDZAI_CVV_RESULT("cvv_result", 1, 1, "The result of the cvv check by the issuer."),
    FEEDZAI_AVS_RESULT("avs_result", 1, 1, "The result of the avs check by the issuer."),
    FEEDZAI_AUTHORIZATION_CODE("authorization_code", 1, 10, "The issuer authorization code."),
    FEEDZAI_ACQUIRER_NAME("acquirer_name", 1, 64, " The acquirer name of the transaction."),

    // Response fields.
    TIMESTAMP("T", 1, 32, "Transaction processing timestamp."),
    RESPONSE_ID("z1", 1, 32, true,"Response ID."),
    RESULT_CODE("z2", 1, 3, true, "Operation result code."),
    RESULT_DESCRIPTION("z3", 5, 256, true, "Operation result description."),
    AUTHORIZATION_CODE("z4", 1, 10, true, "Authorization code, generated by the issuer."),
    RISK_SCORE("z5", 1, 4, "Risk score."),
    PROCESSING_RESPONSE_REASON_CODE("z6", 1, 3, true, "Processing response reason code, generated by the issuer."),
    EXTERNAL_NETWORK_REFERENCE("z7", 6, 32, "External Network Refernece ID, generated by the Card Scheme."),
    AVS_RESPONSE("z9", 2, 2, true, "Address verification service (AVS) authorization response."),
    TRANSACTION_ID("z13", 1, 32, true, "Transaction ID, also known as the Retrieval Reference Number (RRN)."),
    CVV2_RESPONSE_CODE("z14", 1, 1, true, "Processing response reason code, generated by the issuer."),
    APPROVED_BILLING_AMOUNT("z15", 1, 10, true, "Approved billing amount."),
    BALANCE_RESPONSE("z16", 1, 10, true, "Balance response, provided by issuer for card present transactions."),
    BALANCE_RESPONSE_CURRENCY("z17", 3, 3, true,
            "Balance response currency, in case the balance response is returned."),
    PAN("z18", 8, 19, "PAN retrieval opcode 99"),
    EXPYEAR("z19", 2, 2, "Card expiry year retrieval opcode 99"),
    EXPMONTH("z20", 2, 2, "Card expiry month retrieval opcode 99"),
    FRAUD_CHECK_DETAILS("z21", 1, 3,
            "Indicates if the transaction was actually sent to one of the fraud services."),
    CUMULATIVE_AMOUNT("z25", 1, 12, "Updated authorization amount."),
    ROUTING_METHOD("z30", 0, 255, "Chosen routing method - r1/rules/distribution/priority"),
    ACTIVATED_RULE_ID("z31", 0, 4, "Activated rule ID"),
    ROUTED_PROCESSOR("z33", 0, 255, "The processor who processed the transaction."),
    ROUTED_TARGET_MID("z34", 0, 255, "The routed target_mid."),
    RE_ROUTE_FLAG("z35", 4, 4, "Re-route flag as true."),
    FIRST_ROUTED_PROCESSOR("z36", 0, 255, "The first routed processor."),
    FIRST_PROCESSOR_RESPONSE("z37", 0, 255, "The z41 of the first processor response."),
    ROUTING_DECISION_EXPLANATION_ARRAY("z38", 0, 400, "The routing decision explanation array."),
    ACQUIRER_TRANSACTION_ID("z39", 0, 255, "Acquirer transaction ID."),
    FRAUD_EXPLANATION_ARRAY("z40", 0, 400, "Fraud explanation array."),
    EXTERNAL_RESPONSE("z41", 0, 255, true, "External response - the original z6 of the connector."),
    PROCESSOR_TRANSACTION_ID("z42", 0, 255, "APM Method transaction id"),
    SCHEDULED_OFFLINE_RETRY("z43", 0, 1, "Scheduled for offline retry"),
    MERCHANT_ADVICE_CODE("z44", 0, 255, "Merchant advice code."),

    AFB1("afb1", 64, 64, "Allows referrals for non active gw_mid & processor coming from insights."),
    INITIAL_TRANSACTION_ID_RESPONSE("z50", 0, 255, true, "Processing response reason id, generated by the issuer."),
    FAST_FUNDS_INDICATOR("z51", 1, 1, "Fast funds indicator."),
    PAYMENT_ID("z55", 32, 32, "Payment id."),
    ISSUER_COUNTRY("z56", 0, 60, "Issuer country"),
    SCHEME_RESPONSE_CODE("z60", 0, 255, "Original scheme response code as received from connectors"),
    PAYMENT_ACCOUNT_REFERENCE("b20", 1, 29, true, "Payment account reference."),
    TRANSIT_TRANSACTION_TYPE("t1", 2, 2, "Transit transaction type."),
    TRANSIT_TRANSPORTATION_MODE("t2", 2, 2, "Transit transportation mode."),
    COB_ENROLLED("r4", 1, 1, "Cob enrolled."),
    VPOS_SEND_THANK_YOU_EMAIL("vpos_send_thank_you_email", 1, 1,
            "Indicates if sendThankYouEmail is supported and possibly enable relevant functionality."),
    VPOS_SEND_THANK_YOU_EMAIL_ADDRESS("vpos_send_thank_you_email_address", 1, 255,
            "If sendThankYouEmail is supported, this will/should be populated with the email address."),
    SOFT_DECLINE("soft_decline", 2, 2, "Indicates if 3DS was initiated after getting soft decline."),
    COB_NOTIFICATION_ADDRESS("cob_notification_address", 0, 1000, "The merchant notification address for COB.");

    private static final Map<String, EPowerParameter> valueMap = new LinkedHashMap<>(values().length);
    static {
        for (EPowerParameter value : values()) {
            valueMap.put(value.getCode(), value);
        }
    }

    private final String code;
    private final int minLength;
    private final int maxLength;
    private final boolean connectorDriven;
    private final String description;

    EPowerParameter(final String code, final int minLength, final int maxLength, final String description) {
        this(code, minLength, maxLength, false, description);
    }

    EPowerParameter(String code, int minLength, int maxLength, boolean connectorDriven, String description) {
        this.code = code;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.connectorDriven = connectorDriven;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getDescription() {
        return description;
    }

    public boolean isConnectorDriven() {
        return connectorDriven;
    }

    public static EPowerParameter valueOfCode(String code) {
        return valueMap.getOrDefault(code, null);
    }

}
