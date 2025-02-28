import React, { useCallback, useEffect, useState } from 'react';
import { ProductResponse } from "../model/api/ProductResponse";
import { ProductApi } from "../api/ProductApi";
import { UserResponse } from "../model/api/UserResponse";
import { USER } from "../constants/constants";
import { CampaignList } from "./CampaignList";
import {
    AppContainer,
    Section,
    Button,
    ProductListUl,
    ProductItem,
    ProductName,
    AddProductSection,
    ProductInput,
    AddProductButton
} from "../App.styles";

export const ProductList = () => {
    const [user, setUser] = useState<UserResponse | null>(null);
    const [products, setProducts] = useState<ProductResponse[]>([]);
    const [newProductName, setNewProductName] = useState('');
    const [selectedProductId, setSelectedProductId] = useState<number | null>(null);

    const addProduct = useCallback(async () => {
        if (!user || newProductName.trim() === '') {
            return;
        }

        try {
            const response = await ProductApi.addProducts({
                name: newProductName,
                userId: user.id
            });
            setProducts((prevProducts) => {
                console.log("Previous products: ", prevProducts);  // Log previous products
                return [...prevProducts, { id: response.data, name: newProductName }];
            });
            setNewProductName('');
        } catch (error) {
            console.error(error);
        }
    }, [user, newProductName]);

    const getProduct = useCallback(async () => {
        if (!user) return;

        try {
            const response = await ProductApi.getProducts(user.id);
            console.log("Fetched products: ", response);  // Log response data to verify API result
            setProducts(response.data);
        } catch (error) {
            console.error(error);
        }
    }, [user]);

    useEffect(() => {
        const storedUser = localStorage.getItem(USER);
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
    }, []);

    useEffect(() => {
        if (user) {
            getProduct();
        }
    }, [user, getProduct]);

    if (!user) {
        return (
            <Section>
                <h2>Please log in to see the product list.</h2>
            </Section>
        );
    }

    return (
        <AppContainer>
            <Section>
                <h2>Product List</h2>
                <div>
                    <h3>Products</h3>
                    <ProductListUl>
                        {products.length === 0 ? (
                            <li>No products available</li>
                        ) : (
                            products.map((product) => (
                                <ProductItem key={product.id}>
                                    <ProductName >{product.name}</ProductName>  {/* Added temporary background color for visibility */}
                                    <Button onClick={() => setSelectedProductId(selectedProductId === product.id ? null : product.id)}>
                                        {selectedProductId === product.id ? 'Hide Campaigns' : 'View Campaigns'}
                                    </Button>
                                    {selectedProductId === product.id && <CampaignList productId={product.id} />}
                                </ProductItem>
                            ))
                        )}
                    </ProductListUl>
                </div>

                <AddProductSection>
                    <h3>Add New Product</h3>
                    <ProductInput
                        type="text"
                        placeholder="Enter product name"
                        value={newProductName}
                        onChange={(e) => setNewProductName(e.target.value)}
                    />
                    <AddProductButton onClick={addProduct}>Add Product</AddProductButton>
                </AddProductSection>
            </Section>
        </AppContainer>
    );
};

export default ProductList;
