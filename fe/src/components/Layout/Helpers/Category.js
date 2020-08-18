



export const getChildCategories = (parent, categories, children) => {
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