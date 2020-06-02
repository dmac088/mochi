

export const isHomePath = (path) => {
    return path === '/:lang/:curr';
}

export const getHomePath = (match) => {
    const {curr, lang} = match.params;
    return '/' + lang + '/' + curr;
}