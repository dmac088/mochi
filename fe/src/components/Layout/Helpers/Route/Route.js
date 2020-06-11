

export const isHomePath = (path) => {
    return path === '/:lang/:curr';
}

export const getHomePath = (match) => {
    const {curr, lang} = match.params;
    return '/' + lang + '/' + curr;
}

export const getCategoryPath = (categoryDesc, match) => {
    return getHomePath(match) + '/category/' + categoryDesc;
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

export const getAccountSubPath = (match, suffix) => {
    return getAccountPath(match) + suffix;
}