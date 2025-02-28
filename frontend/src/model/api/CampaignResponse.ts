import {Town} from "./Town";

export type CampaignResponse = {
    id: number;
    name: string;
    keywords: string;
    bidAmount: number;
    campaignFund: number;
    status: boolean;
    town: Town;
    radius: number;
};
