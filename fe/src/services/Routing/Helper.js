

export const routeToPage = (history, params, page) => {
    const {lang, curr} = params;
    history.push('/' + lang + '/' + curr + '/' + page);
} 