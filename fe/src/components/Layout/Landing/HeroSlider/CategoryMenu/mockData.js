
export const mockDisplayList = ['FRT01'];

export const mockCategory = {
  "data": {
    "categoryCode": "PRM01",
    "categoryDesc": "ALL",
    "level": 0,
    "categoryType": "productcategory",
    "locale": "en-GB",
    "currency": "HKD",
    "parentCode": null,
    "childCount": 11,
    "count": 29
  },
  "_links": {
    "self": {
      "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PRM01"
    },
    "products": {
      "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PRM01?page=0&size=10"
    }
  }
}

export const mockCategoryList = {
        "_embedded": {
          "categoryResources": [
            {
              "data": {
                "categoryCode": "POM01",
                "categoryDesc": "Pomes",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 3
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/POM01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/POM01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DRU01",
                "categoryDesc": "Drupes",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DRU01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DRU01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "VEG01",
                "categoryDesc": "Vegetables",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 5,
                "count": 12
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/VEG01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/VEG01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "REV01",
                "categoryDesc": "Red",
                "level": 3,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "ROV01",
                "childCount": 1,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/REV01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/REV01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "OFT01",
                "categoryDesc": "Other Fruit",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/OFT01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/OFT01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "ONT01",
                "categoryDesc": "Other Nuts",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 2
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/ONT01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/ONT01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "MEL01",
                "categoryDesc": "Melons",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 2
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/MEL01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/MEL01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "PEC01",
                "categoryDesc": "Pecans",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PEC01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PEC01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "PEA01",
                "categoryDesc": "Peanuts",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PEA01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PEA01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DGV01",
                "categoryDesc": "Dark Green",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "VEG01",
                "childCount": 0,
                "count": 2
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DGV01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DGV01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "ORV01",
                "categoryDesc": "Orange",
                "level": 3,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "ROV01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/ORV01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/ORV01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "BER01",
                "categoryDesc": "Berries",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 2
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/BER01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/BER01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "FBR01",
                "categoryDesc": "Featured Brands",
                "level": 0,
                "categoryType": "brandcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "count": 0,
                "childCount": null
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/FBR01"
                },
                "brands": {
                  "href": "https://localhost:8090/api/Brand/en-GB/HKD/category/FBR01"
                }
              }
            },
            {
              "data": {
                "categoryCode": "BAP01",
                "categoryDesc": "Beans and Peas",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "VEG01",
                "childCount": 0,
                "count": 1
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/BAP01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/BAP01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "PIS01",
                "categoryDesc": "Pistachios",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 1
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PIS01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PIS01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "MAC01",
                "categoryDesc": "Macadamia",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/MAC01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/MAC01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "ALM01",
                "categoryDesc": "Almonds",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 1
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/ALM01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/ALM01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM03",
                "categoryDesc": "DUMMY 3",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM03"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM03?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM04",
                "categoryDesc": "DUMMY 4",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM04"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM04?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "BRA01",
                "categoryDesc": "Brazil",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/BRA01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/BRA01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM01",
                "categoryDesc": "DUMMY 1",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "CAS01",
                "categoryDesc": "Cashews",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 1
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/CAS01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/CAS01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM02",
                "categoryDesc": "DUMMY 2",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM02"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM02?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "CIT01",
                "categoryDesc": "Citrus",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 3
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/CIT01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/CIT01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "SVG01",
                "categoryDesc": "Starchy",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "VEG01",
                "childCount": 0,
                "count": 4
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/SVG01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/SVG01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "BND01",
                "categoryDesc": "All Brands",
                "level": 0,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": null,
                "childCount": 2,
                "count": 4
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/BND01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/BND01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "UNK01",
                "categoryDesc": "Unknown",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM02",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/UNK01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/UNK01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "PRM02",
                "categoryDesc": "MISC",
                "level": 0,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": null,
                "childCount": 3,
                "count": 22
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PRM02"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PRM02?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM09",
                "categoryDesc": "DUMMY 9",
                "level": 4,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "REV01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM09"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM09?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "OTH01",
                "categoryDesc": "Other",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "VEG01",
                "childCount": 0,
                "count": 4
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/OTH01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/OTH01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM07",
                "categoryDesc": "DUMMY 7",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM07"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM07?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "OTH02",
                "categoryDesc": "Other Brands",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "BND01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/OTH02"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/OTH02?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM08",
                "categoryDesc": "DUMMY 8",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM08"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM08?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "PRM01",
                "categoryDesc": "ALL",
                "level": 0,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": null,
                "childCount": 11,
                "count": 29
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/PRM01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/PRM01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM05",
                "categoryDesc": "DUMMY 5",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM05"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM05?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "DUM06",
                "categoryDesc": "DUMMY 6",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/DUM06"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/DUM06?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "FRT01",
                "categoryDesc": "Fruit",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 7,
                "count": 12
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/FRT01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/FRT01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "FET01",
                "categoryDesc": "Featured",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM02",
                "childCount": 0,
                "count": 12
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/FET01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/FET01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "ROV01",
                "categoryDesc": "Red and Orange",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "VEG01",
                "childCount": 2,
                "count": 1
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/ROV01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/ROV01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "HAZ01",
                "categoryDesc": "Hazelnuts",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "NUT01",
                "childCount": 0,
                "count": 0
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/HAZ01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/HAZ01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "TRO01",
                "categoryDesc": "Tropical",
                "level": 2,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "FRT01",
                "childCount": 0,
                "count": 2
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/TRO01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/TRO01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "BSP01",
                "categoryDesc": "Best Sellers",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM02",
                "childCount": 0,
                "count": 10
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/BSP01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/BSP01?page=0&size=10"
                }
              }
            },
            {
              "data": {
                "categoryCode": "NUT01",
                "categoryDesc": "Nuts",
                "level": 1,
                "categoryType": "productcategory",
                "locale": "en-GB",
                "currency": "HKD",
                "parentCode": "PRM01",
                "childCount": 9,
                "count": 5
              },
              "_links": {
                "self": {
                  "href": "https://localhost:8090/api/Category/en-GB/HKD/code/NUT01"
                },
                "products": {
                  "href": "https://localhost:8090/api/Product/en-GB/HKD/category/NUT01?page=0&size=10"
                }
              }
            }
          ]
        },
        "_links": {
          "self": {
            "href": "https://localhost:8090/api/Category/en-GB/HKD/"
          }
        }
      }