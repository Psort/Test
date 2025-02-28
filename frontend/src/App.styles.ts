import styled from "styled-components";

export const AppContainer = styled.div`
    height: 100vh;
    display: flex;
    flex-direction: row-reverse;

    @media (max-width: 960px) {
        flex-direction: column;
    }
`;

export const Section = styled.div`
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

export const Button = styled.button`
    width: 5rem;
    height: 1em;
    background-color: rgba(0, 0, 0, 0.7);
    color: var(--color-primary);
    border-radius: 1rem;
    box-shadow: 0 0 0.25rem var(--color-primary);
    padding: 2rem;
    text-align: center;
    position: relative;
    font-size: 1rem;

    &:hover {
        background-color: var(--color-primary);
        color: #161616;
    }
`;

export const ProductListUl = styled.ul`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* Automatycznie wypełnia przestrzeń */
    gap: 20px; /* Przestrzeń między elementami */
    list-style-type: none;
    padding: 0;
`;

export const ProductItem = styled.li`
    background-color: #f8f8f8;
    color: #100D1A;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    text-align: center;
`;

export const ProductName = styled.h4`
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 10px;
`;

export const AddProductSection = styled.div`
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
`;

export const ProductInput = styled.input`
    padding: 8px;
    font-size: 16px;
    margin-right: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
`;

export const AddProductButton = styled.button`
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;

    &:hover {
        background-color: #45a049;
    }
`;
export const Container = styled.div`
    padding: 20px;
    font-family: Arial, sans-serif;
`;

export const CampaignListContainer = styled.div`
    display: flex;
    flex-wrap: nowrap; /* Elements will not wrap to the next line */
    overflow-x: auto; /* Allows horizontal scrolling if the items exceed container width */
    padding: 20px 0;
    gap: 15px; /* Spacing between items */
    margin-top: 20px;
`;

export const CampaignListItem = styled.div`
    background-color: #f9f9f9;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 200px; /* Fixed width for each campaign */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    flex-shrink: 0; /* Prevents the item from shrinking */
`;

export const Form = styled.div`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    max-width: 500px;
    gap: 10px;
`;

export const Input = styled.input`
    padding: 10px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    width: 100%;
    box-sizing: border-box;
`;

export const Select = styled.select`
    padding: 10px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    width: 100%;
    box-sizing: border-box;
`;
