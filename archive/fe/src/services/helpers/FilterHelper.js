



export const filterCategories = (categoryList, code) => {
  return categoryList.filter(function(value, index, arr){
    //console.log(value);
    return value.payload.layoutCodes.filter(function(value, index, arr){
      return value.code === code;
    }).length > 0;
  });
}
