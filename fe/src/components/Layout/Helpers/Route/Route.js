

export const isHomePath = (path) => {
    return path === '/:lang/:curr';
}

export const getHomePath = (match) => {
    const {curr, lang} = match.params;
    return '/' + lang + '/' + curr;
}

export const getCategoryPath = (categoryCode, match) => {
    return getHomePath(match) + '/category/' + categoryCode + '?page=0&size=10&sort=nameAsc';
}

export const getCategoryProductPath = (productCode, match) => {
    const { categoryCode } = match.params;
    return getHomePath(match) + '/category/' + categoryCode + '/product/' + productCode;
}

export const getCategoryProductPathForCategoryCode = (productCode, categoryCode,  match) => {
    console.log(match);
    return getHomePath(match) + '/category/' + categoryCode + '/product/' + productCode;
}

export const getContactPath = (match) => {
    return getHomePath(match) + '/contact';
}

export const getBagPath = (match) => {
    return getHomePath(match) + '/mybag';
}

export const getCheckoutPath = (match) => {
    return getHomePath(match) + '/mycheckout';
}

export const getAuthPath = (match) => {
    return getHomePath(match) + '/auth';
}

export const getAccountPath = (match) => {
    return getHomePath(match) + '/myaccount';
}

export const getProductPath = (match) => {
    return getHomePath(match) + '/product';
}

export const getAccountSubPath = (match, suffix) => {
    return getAccountPath(match) + '/' + suffix;
}