

export const findByDesc = (categories, desc) => {
    if (!categories) { return; }
    if(!desc) { return; }
    return categories.filter(o => o.data.categoryDesc === desc)[0];
}

export const findByCode = (categories, code) => {
    if (!categories) { return; }
    if(!code) { return; }
    return categories.filter(o => o.data.categoryCode === code)[0];
}

export const getChildCategories = (parent = {}, categories = [], children = []) => {
    const c = categories.filter(o => o.data.parentCode === parent.data.categoryCode);
    if (c.length === 0) {
        return children;
    }
    c.map((child) => {
        children.push(child);
        getChildCategories(child, categories, children);
    });
    return c; 
}