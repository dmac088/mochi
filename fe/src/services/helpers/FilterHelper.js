



export const filterCategories = (categoryList, code) => {
  return categoryList.filter(function(value, index, arr){
    return value.entity.layouts.filter(function(value, index, arr){
      return value.code === code;
    }).length > 0;
  });
}
