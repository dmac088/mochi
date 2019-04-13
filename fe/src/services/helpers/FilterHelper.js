



export const filterCategories = (categoryList) => {
  return categoryList.filter(function(value, index, arr){
    return value.layouts.filter(function(value, index, arr){
      return value.code === 'LNDMM01';
    }).length > 0;
  });
}
