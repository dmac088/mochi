import React from 'react';

export const Spinner = () => {
    return (
        <div className="sidebar mb-35">
            <div className="d-flex justify-content-center">
                <div className="spinner-border text-success" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            </div>
        </div>
    );
}