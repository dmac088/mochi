

export const isHomePath = (path) => {
    return path === '/:lang/:curr';
}

export const getHomePath = (match) => {
    if(!match) {
        return `/en-GB/HKD`;
    }
    const {curr, lang} = match.params;
    return `/${lang}/${curr}`;
}

export const getCategoryPath = (categoryCode, match) => {
    return getHomePath(match) + '/browse/category/' + categoryCode + '?page=0&size=10&sort=nameAsc';
}

export const getCategoryProductPath = (match, productCode, catCode) => {
    const { categoryCode } = match.params;
    return getHomePath(match) + '/category/' + (categoryCode || catCode) + '/product/' + productCode;
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

export const getForgotPath = (match) => {
    return getHomePath(match) + '/forgot';
}

export const getAccountPath = (match) => {
    return getHomePath(match) + '/myaccount';
}

export const getProductPath = (match) => {
    return getHomePath(match) + '/product';
}

//:lang/:curr/search/category/:categoryCode
export const getSearchPath = (match, term) => {
    return getHomePath(match) + `/search/category/PRM01?q=${term}&page=0&size=10&sort=bestMatch`;
}

export const getAccountSubPath = (match, suffix) => {
    return getAccountPath(match) + '/' + suffix;
}