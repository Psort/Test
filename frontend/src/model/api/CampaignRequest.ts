import {Town} from "./Town";

export type CampaignRequest = {
    name:string,
    keywords:string,
    bidAmount : number,
    campaignFund: number,
    status: boolean,
    town : Town,
    radius: number,
    productId: number
};
