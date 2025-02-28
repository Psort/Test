import { authorizedApi } from "../hooks/withAxiosIntercepted";
import { CampaignRequest} from "../model/api/CampaignRequest";
import {CampaignResponse} from "../model/api/CampaignResponse";

export class CampaignApi {
    static getCampaigns = async (productId: number) =>
        await authorizedApi.get<CampaignResponse[]>(`/campaigns/${productId}`);

    static addCampaign = async (campaign: CampaignRequest) =>
        await authorizedApi.post<CampaignResponse>(`/campaigns`, campaign);

    static updateCampaign = async (id: number, campaign: CampaignRequest) =>
        await authorizedApi.put<CampaignResponse>(`/campaigns/${id}`, campaign);

    static deleteCampaign = async (id: number) =>
        await authorizedApi.delete<CampaignResponse>(`/campaigns/${id}`);
}
