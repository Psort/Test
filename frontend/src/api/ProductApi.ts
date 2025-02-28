import {authorizedApi} from "../hooks/withAxiosIntercepted";
import {ProductResponse} from "../model/api/ProductResponse";
import {ProductRequest} from "../model/api/ProductRequest";

export class ProductApi {
    static getProducts = async (userId: number | undefined) =>
        await authorizedApi.get<ProductResponse[]>(`/products/${userId}`);

    static addProducts = async (product: ProductRequest) =>
        await authorizedApi.post<number>(`/products`, product);
}
